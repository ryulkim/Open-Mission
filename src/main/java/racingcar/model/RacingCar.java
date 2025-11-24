package racingcar.model;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

public class RacingCar {
    @Getter
    String name;
    AtomicInteger status;
    Car car;

    public RacingCar(Car car) {
        this.name = car.name;
        this.car = car;
        this.status = new AtomicInteger(0);
    }

    public static RacingCar createCar(Car car) {
        return new RacingCar(car);
    }

    public void move(int go) {
        status.addAndGet(go);
    }

    public Car getCar() {
        return new Car(car.name, car.speed, car.power, car.maxLuck, car.carSpec);
    }

    public int getStatus() {
        return status.get();
    }

}
