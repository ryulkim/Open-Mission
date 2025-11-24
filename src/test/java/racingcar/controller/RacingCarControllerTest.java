package racingcar.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.model.Car;
import racingcar.model.RacingCar;
import racingcar.service.RacingCarService;
import racingcar.util.Print;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@ExtendWith(MockitoExtension.class)
class RacingCarControllerTest {

    @Mock
    RacingCarService racingCarService;

    @Test
    @DisplayName("run()에서 모드 선택 후 InputView.close()가 호출된다 (mode=1, singleMode=4 로 즉시 종료)")
    void run_호출시_close_호출() {
        try (MockedStatic<InputView> inputViewMock = mockStatic(InputView.class)) {
            // given
            RacingCarController controller = new RacingCarController(racingCarService);

            inputViewMock.when(InputView::inputMode).thenReturn(1);      // selectMode() -> single mode 진입
            inputViewMock.when(InputView::inputSingleMode).thenReturn(4); // 바로 종료
            inputViewMock.when(InputView::close).thenCallRealMethod();   // 그냥 호출만 검증

            // when
            controller.run();

            // then
            inputViewMock.verify(InputView::inputMode);
            inputViewMock.verify(InputView::inputSingleMode);
            inputViewMock.verify(InputView::close);
        }
    }

    @Test
    @DisplayName("singleMode=2: 자동차 목록을 출력한다")
    void singleMode_2_자동차_리스트_출력() {
        try (MockedStatic<InputView> inputViewMock = mockStatic(InputView.class);
             MockedStatic<OutputView> outputViewMock = mockStatic(OutputView.class)) {

            // given
            RacingCarController controller = new RacingCarController(racingCarService);

            // mode 1 선택 -> single mode 진입
            inputViewMock.when(InputView::inputMode).thenReturn(1);
            // singleMode: 2(리스트 출력) -> 4(종료)
            inputViewMock.when(InputView::inputSingleMode).thenReturn(2, 4);
            inputViewMock.when(InputView::close).thenCallRealMethod();

            List<Car> cars = Collections.emptyList();
            when(racingCarService.getCars()).thenReturn(cars);

            // when
            controller.run();

            // then
            verify(racingCarService, atLeastOnce()).getCars();
            outputViewMock.verify(() -> OutputView.printCars(cars));
        }
    }

    @Test
    @DisplayName("singleMode=1: 라운드 수만큼 round()를 호출하고 최종 우승자를 출력한 뒤 racingCarClear()를 호출한다")
    void singleMode_1_게임_진행_및_우승자_출력() {
        try (MockedStatic<InputView> inputViewMock = mockStatic(InputView.class);
             MockedStatic<OutputView> outputViewMock = mockStatic(OutputView.class);
             MockedStatic<Print> printMock = mockStatic(Print.class)) {

            // given
            RacingCarController controller = new RacingCarController(racingCarService);

            // 모드 1 선택
            inputViewMock.when(InputView::inputMode).thenReturn(1);
            // singleMode: 1(게임) -> 4(종료)
            inputViewMock.when(InputView::inputSingleMode).thenReturn(1, 4);

            // 자동차 선택 & 라운드 수
            when(racingCarService.getCarSize()).thenReturn(3);
            inputViewMock.when(() -> InputView.inputSelectCar(anyInt())).thenReturn(2); // 두 번째 자동차 선택
            inputViewMock.when(InputView::inputNum).thenReturn(2); // 2라운드 진행
            inputViewMock.when(InputView::close).thenCallRealMethod();

            // round()와 getWinners() 동작 mock
            List<RacingCar> dummyRoundCars = Collections.emptyList();
            when(racingCarService.round()).thenReturn(dummyRoundCars);
            when(racingCarService.getWinners()).thenReturn("pobi");
            when(racingCarService.getCarName(2)).thenReturn("pobi");

            // when
            controller.run();

            // then
            // game(round) 안에서 round()가 라운드 수(2) 만큼 호출되었는지
            verify(racingCarService, times(2)).round();
            // 우승자 조회
            verify(racingCarService, times(1)).getWinners();
            // 선택한 자동차 이름 조회
            verify(racingCarService, times(1)).getCarName(2);
            // 최종 우승자 출력
            outputViewMock.verify(() -> OutputView.finalWinner("pobi", "pobi"));
            // 게임 이후 racingCarClear 호출
            verify(racingCarService, times(1)).racingCarClear();
        }
    }

    @Test
    @DisplayName("singleMode=3: 커스텀 자동차 추가 후 목록 출력, 예외 발생 시 메시지 출력 후 재시도")
    void singleMode_3_커스텀_자동차_추가() {
        try (MockedStatic<InputView> inputViewMock = mockStatic(InputView.class);
             MockedStatic<OutputView> outputViewMock = mockStatic(OutputView.class);
             MockedStatic<Print> printMock = mockStatic(Print.class)) {

            // given
            RacingCarController controller = new RacingCarController(racingCarService);

            inputViewMock.when(InputView::inputMode).thenReturn(1);
            // singleMode: 3(커스텀 추가) -> 4(종료)
            inputViewMock.when(InputView::inputSingleMode).thenReturn(3, 4);
            inputViewMock.when(InputView::close).thenCallRealMethod();

            Car validCar = mock(Car.class);

            // 첫 번째 시도: InputView.inputCustomCar() -> 예외
            // 두 번째 시도: 성공적으로 Car 반환
            inputViewMock.when(InputView::inputCustomCar)
                    .thenThrow(new IllegalArgumentException("error"))
                    .thenReturn(validCar);

            // addCustomCar는 void이므로 doNothing
            // 예외는 controller 내부에서 try-catch 처리하므로 여기선 성공 케이스만 stub
            List<Car> carsAfterAdd = List.of(validCar);
            when(racingCarService.getCars()).thenReturn(carsAfterAdd);

            // when
            controller.run();

            // then
            // inputCustomCar는 예외/성공 포함 2번 호출
            inputViewMock.verify(InputView::inputCustomCar, times(2));
            // 예외 발생 시 메시지를 출력
            printMock.verify(() -> Print.println("error"));
            // 최종적으로 addCustomCar가 한 번 호출
            verify(racingCarService, times(1)).addCustomCar(validCar);
            // 자동차 목록 출력
            outputViewMock.verify(() -> OutputView.printCars(carsAfterAdd), times(1));
        }
    }
}
