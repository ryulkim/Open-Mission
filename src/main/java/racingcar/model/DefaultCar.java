package racingcar.model;

import racingcar.common.CarSpec;

public class DefaultCar extends Car {

    DefaultCar(String name, int speed, int power, int maxLuck) {
        super(name, speed, power, maxLuck, CarSpec.DEFAULT);
    }

}
