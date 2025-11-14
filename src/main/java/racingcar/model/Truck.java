package racingcar.model;

import racingcar.common.CarSpec;

public class Truck extends Car {
    Truck(String name, int speed, int power, int maxLuck) {
        super(name, speed, power, maxLuck, CarSpec.TRUCK);
    }

}
