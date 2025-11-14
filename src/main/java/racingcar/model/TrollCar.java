package racingcar.model;

import racingcar.common.CarSpec;

public class TrollCar extends Car {
    TrollCar(String name, int speed, int power, int maxLuck) {
        super(name, speed, power, maxLuck, CarSpec.TROLL);
    }

}
