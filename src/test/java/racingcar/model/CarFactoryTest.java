package racingcar.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.common.CarSpec;

public class CarFactoryTest {
    @ParameterizedTest
    @ValueSource(strings = {"DEFAULT", "TRUCK", "TROLL"})
    @DisplayName("정상_CAR_객체_생성: 타입에 맞는 CarSpec으로 생성된다")
    public void 정상_CAR_객체_생성(String carType) {
        // given
        CarSpec carSpec = CarSpec.valueOf(carType);

        // when
        Car car = CarFactory.createCar("popo", carSpec, 1, 1, 1);

        // then
        Assertions.assertEquals(carSpec, car.getCarSpec());
    }

    @ParameterizedTest
    @CsvSource({
            "DEFAULT, -10, 0, 0",
            "DEFAULT, 0, 101, 0",
            "TROLL, -101, 0, 0",
            "TROLL, 0, 100000, 0",
            "TROLL, 0, 0, 100000",
            "TRUCK, 100, 0, 0",
            "TRUCK, 0, 200000, 0",
            "TRUCK, 0, 0, -10",
    })
    @DisplayName("오류_CAR_최소_최대_범위_아닐_경우: 범위를 벗어나면 예외가 발생한다")
    public void 오류_CAR_최소_최대_범위_아닐_경우(String carType, int speed, int power, int luck) {
        // given
        CarSpec carSpec = CarSpec.valueOf(carType);

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> CarFactory.createCar("popo", carSpec, speed, power, luck));
    }

    @ParameterizedTest
    @CsvSource({
            // 각 CarSpec의 min/max 경계값 조합 (가능한 한 유효한 조합으로 구성)
            "DEFAULT, 0, 0, 0",
            "DEFAULT, 5, 100, 150",
            "TRUCK, 0, 0, 0",
            "TRUCK, 3, 10000, 180",
            "TROLL, -5, 0, 0",
            "TROLL, 1, 100, 200"
    })
    @DisplayName("경계값_CAR_생성: 각 CarSpec의 허용 범위 경계에서 정상 생성된다")
    public void 경계값_CAR_생성(String carType, int speed, int power, int luck) {
        // given
        CarSpec carSpec = CarSpec.valueOf(carType);

        // when
        Car car = CarFactory.createCar("popo", carSpec, speed, power, luck);

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(carSpec, car.getCarSpec()),
                () -> Assertions.assertEquals(speed, car.getSpeed()),
                () -> Assertions.assertEquals(power, car.getPower()),
                () -> Assertions.assertEquals(luck, car.getMaxLuck())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "DEFAULT, DefaultCar",
            "TRUCK, Truck",
            "TROLL, TrollCar"
    })
    @DisplayName("CarSpec에 맞는 구체 Car 구현체가 생성된다")
    public void CarSpec별_구현체_검증(String carType, String expectedSimpleClassName) {
        // given
        CarSpec carSpec = CarSpec.valueOf(carType);

        // when
        Car car = CarFactory.createCar("popo", carSpec, 1, 1, 1);

        // then
        Assertions.assertEquals(expectedSimpleClassName, car.getClass().getSimpleName());
    }
}
