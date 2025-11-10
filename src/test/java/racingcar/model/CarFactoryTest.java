package racingcar.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
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


}
