package racingcar.model;

import static racingcar.common.Exception.EXCEED_CAR_LUCK;
import static racingcar.common.Exception.EXCEED_CAR_POWER;
import static racingcar.common.Exception.EXCEED_CAR_SPEED;

public class Truck extends Car {

    private final int MAX_SPEED = 50;
    private final int MIN_SPEED = -10;
    private final int MAX_POWER = 10000;
    private final int MIN_POWER = 0;
    private final int MAX_LUCK = 200;
    private final int MIN_LUCK = 0;

    Truck(int speed, int power, int maxLuck) {
        super(speed, power, maxLuck);
    }

    @Override
    void setSpeed(int speed) {
        if (speed < MIN_SPEED || speed > MAX_SPEED) {
            throw new IllegalArgumentException(EXCEED_CAR_SPEED.toString());
        }
        this.speed = speed;
    }

    @Override
    void setPower(int power) {
        if (power < MIN_POWER || power > MAX_POWER) {
            throw new IllegalArgumentException(EXCEED_CAR_POWER.toString());
        }
        this.power = power;
    }

    @Override
    void setMaxLuck(int maxLuck) {
        if (maxLuck < MIN_LUCK || maxLuck > MAX_LUCK) {
            throw new IllegalArgumentException(EXCEED_CAR_LUCK.toString());
        }
        this.maxLuck = maxLuck;
    }
}
