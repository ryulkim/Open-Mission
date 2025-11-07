package racingcar.model;

public class RacingCar {
    String name;
    int status;

    public RacingCar(String name) {
        this.name = name;
    }

    public static RacingCar createCar(String carName) {
        return new RacingCar(carName);
    }

    public void incrementStatus() {
        status++;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }
}
