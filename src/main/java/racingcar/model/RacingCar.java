package racingcar.model;

public class RacingCar {
    String name;
    int status;

    public RacingCar(String name) {
        this.name = name;
    }

    public static RacingCar createCar(Car car) {
        return new RacingCar(car.getCarSpec().name());
    }

    public void move() {
        status++;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }
}
