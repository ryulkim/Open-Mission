package racingcar.model;

import static racingcar.common.Exception.EXCEED_CAR_LUCK;
import static racingcar.common.Exception.EXCEED_CAR_POWER;
import static racingcar.common.Exception.EXCEED_CAR_SPEED;

import lombok.Getter;
import racingcar.common.CarSpec;

@Getter
public class Car {
    protected int speed;
    protected int power;
    protected int maxLuck;
    protected CarSpec carSpec;

    Car(int speed, int power, int maxLuck, CarSpec carSpec) {
        this.carSpec = carSpec;
        setSpeed(speed);
        setPower(power);
        setMaxLuck(maxLuck);
    }

    void setSpeed(int speed) {
        if (speed < carSpec.minSpeed || speed > carSpec.maxSpeed) {
            throw new IllegalArgumentException(EXCEED_CAR_SPEED.toString());
        }
        this.speed = speed;
    }

    void setPower(int power) {
        if (power < carSpec.minPower || power > carSpec.maxPower) {
            throw new IllegalArgumentException(EXCEED_CAR_POWER.toString());
        }
        this.power = power;
    }

    void setMaxLuck(int maxLuck) {
        if (maxLuck < carSpec.minLuck || maxLuck > carSpec.maxLuck) {
            throw new IllegalArgumentException(EXCEED_CAR_LUCK.toString());
        }
        this.maxLuck = maxLuck;
    }
}
