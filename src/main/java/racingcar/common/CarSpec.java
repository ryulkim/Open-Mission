package racingcar.common;

import lombok.Getter;

@Getter
public enum CarSpec {
    DEFAULT(0, 100, 0, 100, 0, 100),
    TRUCK(-10, 50, 0, 10000, 0, 200),
    TROLL(-100, 5, 0, 100, 0, 10000);

    final int minSpeed;
    final int maxSpeed;
    final int minPower;
    final int maxPower;
    final int minLuck;
    final int maxLuck;

    CarSpec(int minSpeed, int maxSpeed, int minPower, int maxPower, int minLuck, int maxLuck) {
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.minPower = minPower;
        this.maxPower = maxPower;
        this.minLuck = minLuck;
        this.maxLuck = maxLuck;
    }

}
