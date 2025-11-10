package racingcar.model;

import racingcar.common.CarSpec;

public class Truck extends Car {
    Truck(int speed, int power, int maxLuck) {
        super(speed, power, maxLuck, CarSpec.TRUCK);
    }

}
