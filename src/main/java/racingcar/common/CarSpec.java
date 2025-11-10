package racingcar.common;

import static racingcar.common.Exception.NO_CAR_TYPE;

import racingcar.util.Print;

public enum CarSpec {
    DEFAULT(0, 100, 0, 100, 0, 100),
    TRUCK(-10, 50, 0, 10000, 0, 200),
    TROLL(-100, 5, 0, 100, 0, 10000);

    public final int minSpeed;
    public final int maxSpeed;
    public final int minPower;
    public final int maxPower;
    public final int minLuck;
    public final int maxLuck;

    CarSpec(int minSpeed, int maxSpeed, int minPower, int maxPower, int minLuck, int maxLuck) {
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.minPower = minPower;
        this.maxPower = maxPower;
        this.minLuck = minLuck;
        this.maxLuck = maxLuck;
    }

    public static CarSpec parseCarSpec(String input) {
        try {
            return CarSpec.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            Print.println(NO_CAR_TYPE.toString());
            throw new IllegalArgumentException(NO_CAR_TYPE.toString());
        }
    }

}
