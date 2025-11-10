package racingcar.model;

import racingcar.common.CarSpec;

public class DefaultCar extends Car {

    DefaultCar(int speed, int power, int maxLuck) {
        super(speed, power, maxLuck, CarSpec.DEFAULT);
    }

}
