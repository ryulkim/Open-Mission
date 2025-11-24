package racingcar.model;

import static racingcar.common.Exception.NO_CAR_TYPE;

import racingcar.common.CarSpec;

public final class CarFactory {
    public static Car createCar(String carName, CarSpec carType, int speed, int power, int maxLuck) {
        if (carType.equals(CarSpec.DEFAULT)) {
            return new DefaultCar(carName, speed, power, maxLuck);
        }
        if (carType.equals(CarSpec.TRUCK)) {
            return new Truck(carName, speed, power, maxLuck);
        }
        if (carType.equals(CarSpec.TROLL)) {
            return new TrollCar(carName, speed, power, maxLuck);
        }
        throw new IllegalArgumentException(NO_CAR_TYPE.toString());
    }

}
