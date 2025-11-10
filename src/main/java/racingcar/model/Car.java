package racingcar.model;

import lombok.Getter;

@Getter
abstract class Car {
    protected int speed;
    protected int power;
    protected int maxLuck;

    Car(int speed, int power, int maxLuck) {
        setSpeed(speed);
        setPower(power);
        setMaxLuck(maxLuck);
    }

    abstract void setSpeed(int speed);

    abstract void setPower(int power);

    abstract void setMaxLuck(int maxLuck);
}
