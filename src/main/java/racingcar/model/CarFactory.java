package racingcar.model;

import static racingcar.common.Exception.NO_CAR_TYPE;

public final class CarFactory {
    public static Car createCar(String carType, int speed, int power, int maxLuck) {
        if ("DefaultCar".equals(carType)) {
            return new DefaultCar(speed, power, maxLuck);
        }
        if ("Truck".equals(carType)) {
            return new Truck(speed, power, maxLuck);
        }
        throw new IllegalArgumentException(NO_CAR_TYPE + carType);
    }

}
