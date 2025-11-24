package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.common.CarSpec;
import racingcar.model.Car;
import racingcar.model.CarFactory;
import racingcar.model.RacingCar;

class RacingCarServiceTest {

    @Test
    @DisplayName("생성 시 기본 자동차 3대가 cars 리스트에 준비되어 있다")
    void 생성자_기본_자동차_3대_준비() {
        // given & when
        RacingCarService service = new RacingCarService();

        // then
        assertThat(service.getCars())
                .as("기본 자동차는 3대여야 한다")
                .hasSize(3)
                .extracting(Car::getName)
                .containsExactlyInAnyOrder("soni", "pobi", "crong");
    }

    @Test
    @DisplayName("initRacingCars() 호출 시 cars 기준으로 racingCars가 초기화된다")
    void initRacingCars_기본_자동차로_racingCars_초기화() {
        // given
        RacingCarService service = new RacingCarService();

        // when
        service.initRacingCars();

        // then
        assertThat(service.racingCars)
                .as("racingCars의 크기는 cars의 크기와 동일해야 한다")
                .hasSize(service.getCars().size());
        assertThat(service.racingCars)
                .extracting(RacingCar::getName)
                .containsExactlyInAnyOrder("soni", "pobi", "crong");
    }

    @Test
    @DisplayName("initRacingCars() 이후 racingCarClear() 호출 시 racingCars 컬렉션이 비워진다")
    void 자동차_초기화_후_clear_호출_시_리스트_비움() {
        // given
        RacingCarService service = new RacingCarService();
        service.initRacingCars();

        // when
        service.racingCarClear();

        // then
        assertThat(service.racingCars)
                .as("clear 호출 후에는 racingCars가 비어 있어야 한다")
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("racingCarClear()를 먼저 호출해도 예외 없이 동작하고 racingCars는 비어 있어야 한다")
    void 초기화_없이_clear_호출_시_예외_없이_빈_리스트() {
        // given
        RacingCarService service = new RacingCarService();

        // when
        service.racingCarClear();

        // then
        assertThat(service.racingCars)
                .as("초기화하지 않고 clear를 호출해도 racingCars는 비어 있어야 한다")
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("initRacingCars()를 여러 번 호출해도 racingCars 컬렉션은 정상적으로 재초기화된다")
    void 자동차_여러번_초기화_재초기화_검증() {
        // given
        RacingCarService service = new RacingCarService();

        // when
        service.initRacingCars();
        int firstSize = service.racingCars.size();

        service.initRacingCars();
        int secondSize = service.racingCars.size();

        // then
        assertThat(firstSize)
                .as("첫 번째 초기화 이후 자동차 개수는 0보다 커야 한다")
                .isGreaterThan(0);
        assertThat(secondSize)
                .as("두 번째 초기화 이후 자동차 개수도 0보다 커야 한다")
                .isGreaterThan(0);
        assertThat(secondSize)
                .as("여러 번 초기화해도 크기는 동일해야 한다")
                .isEqualTo(firstSize);
    }

    @Test
    @DisplayName("round()는 각 RacingCar의 move를 호출하고, 수정 불가능한 리스트를 반환한다")
    void round_호출_시_이동_및_불변_리스트_반환() {
        // given
        RacingCarService service = new RacingCarService();
        service.initRacingCars();
        List<RacingCar> beforeView = List.copyOf(service.racingCars); // 요소 스냅샷

        // when
        List<RacingCar> result = service.round();

        // then
        // 1) 반환된 리스트는 racingCars와 같은 요소를 가진 뷰여야 한다
        assertThat(result)
                .as("round()는 racingCars와 동일한 요소를 담은 뷰를 반환해야 한다")
                .containsExactlyElementsOf(service.racingCars);

        // 2) 반환된 리스트는 수정할 수 없어야 한다
        assertThat(result)
                .as("반환된 리스트는 수정할 수 없어야 한다")
                .isUnmodifiable();

        // 3) move가 실제로 호출되어 status가 변경되었는지 (적어도 하나는 변경되었는지) 확인
        boolean movedExists =
                service.racingCars.stream().anyMatch(car -> car.getStatus() != 0);
        assertThat(movedExists)
                .as("round() 호출 후에는 적어도 한 자동차의 status가 변경되어야 한다(랜덤에 의존하므로 실패 가능성이 있으면 이 검증은 선택)")
                .isTrue();
    }

    @Test
    @DisplayName("getWinners()는 status가 가장 높은 RacingCar의 이름을 반환한다 (랜덤 개입 없는 경우)")
    void getWinners_가장_먼저_간_자동차_선정() {
        // given
        RacingCarService service = new RacingCarService();
        service.initRacingCars();
        List<RacingCar> racingCars = service.racingCars;

        // 기본 값: 모두 status = 0
        // pobi만 멀리 보내면, 랜덤 로직 없이 status 비교만으로 우승자 결정
        RacingCar soni = racingCars.stream().filter(rc -> rc.getName().equals("soni")).findFirst().orElseThrow();
        RacingCar pobi = racingCars.stream().filter(rc -> rc.getName().equals("pobi")).findFirst().orElseThrow();
        RacingCar crong = racingCars.stream().filter(rc -> rc.getName().equals("crong")).findFirst().orElseThrow();

        soni.move(1);
        pobi.move(3);   // 가장 멀리
        crong.move(2);

        // when
        String winner = service.getWinners();

        // then
        assertThat(winner).isEqualTo("pobi");
    }

    @Test
    @DisplayName("addCustomCar()로 자동차를 추가하면 cars와 getCarSize()에 반영된다")
    void addCustomCar_성공적으로_자동차_추가() {
        // given
        RacingCarService service = new RacingCarService();
        int beforeSize = service.getCarSize();

        Car custom = CarFactory.createCar("jun", CarSpec.DEFAULT, 3, 100, 100);

        // when
        service.addCustomCar(custom);

        // then
        assertThat(service.getCarSize()).isEqualTo(beforeSize + 1);
        assertThat(service.getCars())
                .extracting(Car::getName)
                .contains("jun");
    }

    @Test
    @DisplayName("addCustomCar()에 중복된 이름이 들어오면 예외를 던진다")
    void addCustomCar_중복_이름_예외() {
        // given
        RacingCarService service = new RacingCarService();
        // 기본 cars 리스트에 이미 존재하는 이름: soni, pobi, crong 중 하나 사용
        Car duplicated = CarFactory.createCar("soni", CarSpec.DEFAULT, 3, 100, 100);

        // when & then
        assertThatThrownBy(() -> service.addCustomCar(duplicated))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("getCars()는 내부 cars 리스트를 수정할 수 없는 컬렉션으로 반환한다")
    void getCars_불변_리스트_반환() {
        // given
        RacingCarService service = new RacingCarService();

        // when
        List<Car> cars = service.getCars();

        // then
        assertThat(cars)
                .as("getCars() 반환 리스트는 수정 불가능해야 한다")
                .isUnmodifiable();
    }

    @Test
    @DisplayName("getCarSize()는 cars 리스트의 크기를 반환한다")
    void getCarSize_cars_크기_반환() {
        // given
        RacingCarService service = new RacingCarService();
        int expected = service.getCars().size();

        // when
        int size = service.getCarSize();

        // then
        assertThat(size).isEqualTo(expected);
    }

    @Test
    @DisplayName("getCarName(idx)는 racingCars에서 1-based index로 이름을 조회한다")
    void getCarName_1_base_index() {
        // given
        RacingCarService service = new RacingCarService();
        service.initRacingCars();

        // when
        String firstName = service.getCarName(1);
        String secondName = service.getCarName(2);
        String thirdName = service.getCarName(3);

        // then
        assertThat(List.of(firstName, secondName, thirdName))
                .containsExactlyInAnyOrder("soni", "pobi", "crong");
    }
}
