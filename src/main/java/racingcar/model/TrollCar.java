package racingcar.model;

import racingcar.common.CarSpec;

public class TrollCar extends Car {
    TrollCar(int speed, int power, int maxLuck) {
        super(speed, power, maxLuck, CarSpec.TROLL);
    }

}
