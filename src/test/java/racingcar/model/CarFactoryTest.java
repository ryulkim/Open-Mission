package racingcar.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.common.CarSpec;

public class CarFactoryTest {
    @ParameterizedTest
    @ValueSource(strings = {"DEFAULT", "TRUCK", "TROLL"})
    public void 정상_CAR_객체_생성(String carType) {
        // given
        CarSpec carSpec = CarSpec.valueOf(carType);

        // when
        Car car = CarFactory.createCar(carSpec, 5, 5, 5);

        // then
        Assertions.assertEquals(carSpec, car.getCarSpec());
    }

    @ParameterizedTest
    @CsvSource({
            "DEFAULT, -10, 0, 0",
            "DEFAULT, 0, 101, 0",
            "DEFAULT, 0, 0, 102",
            "TROLL, -101, 0, 0",
            "TROLL, 0, 100000, 0",
            "TROLL, 0, 0, 100000",
            "TRUCK, 100, 0, 0",
            "TRUCK, 0, 200000, 0",
            "TRUCK, 0, 0, -10",
    })
    public void 오류_CAR_최소_최대_범위_아닐_경우(String carType, int speed, int power, int luck) {
        // given
        CarSpec carSpec = CarSpec.valueOf(carType);

        // when

        // then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> CarFactory.createCar(carSpec, speed, power, luck));
    }

}
