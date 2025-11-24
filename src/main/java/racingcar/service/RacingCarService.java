package racingcar.service;

import static racingcar.common.Exception.DUPLICATE_CAR_NAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.common.CarSpec;
import racingcar.model.Car;
import racingcar.model.CarFactory;
import racingcar.model.RacingCar;
import racingcar.util.Randoms;

public class RacingCarService {
    List<RacingCar> racingCars;
    List<Car> cars;

    public RacingCarService() {
        racingCars = new ArrayList<>();
        cars = new ArrayList<>();
        cars.add(CarFactory.createCar("soni", CarSpec.DEFAULT, 3, 100, 130));
        cars.add(CarFactory.createCar("pobi", CarSpec.TRUCK, 2, 1000, 180));
        cars.add(CarFactory.createCar("crong", CarSpec.TROLL, -3, 100, 200));
    }

    public List<RacingCar> round() {
        racingCars.forEach(racingCar -> {
            racingCar.move(calculateGo(racingCar.getCar()));
        });
        return Collections.unmodifiableList(racingCars);
    }

    public String getWinners() {
        sortRacingCar();
        return racingCars.getFirst().getName();
    }

    public void initRacingCars() {
        racingCars.clear();
        cars.stream().map(RacingCar::createCar).forEach(racingCars::add);
    }

    public void addCustomCar(Car car) {
        if (duplicateCarName(car.getName())) {
            throw new IllegalArgumentException(DUPLICATE_CAR_NAME.toString());
        }
        cars.add(car);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void racingCarClear() {
        racingCars.clear();
    }

    public int getCarSize() {
        return cars.size();
    }

    public String getCarName(int idx) {
        return racingCars.get(idx - 1).getName();
    }

    private void sortRacingCar() {
        racingCars.sort((a, b) -> {
            if (a.getStatus() == b.getStatus() && a.getCar().getPower() == b.getCar().getPower()) {
                int aResult = Randoms.pickNumberInRange(a.getCar().getCarSpec().minLuck, a.getCar().getMaxLuck());
                int bResult = Randoms.pickNumberInRange(b.getCar().getCarSpec().minLuck, b.getCar().getMaxLuck());
                return Integer.compare(aResult, bResult);
            }
            if (a.getStatus() == b.getStatus()) {
                return Integer.compare(a.getCar().getPower(), b.getCar().getPower());
            }
            return Integer.compare(b.getStatus(), a.getStatus());
        });
    }

    private int calculateGo(Car car) {
        if (car.getCarSpec() == CarSpec.TROLL) {
            return Randoms.pickNumberInRange(car.getSpeed(), -car.getSpeed()) * (Randoms.pickNumberInRange(0,
                    car.getMaxLuck()) / 100);
        }
        return car.getSpeed() * Randoms.pickNumberInRange(0, car.getMaxLuck());
    }

    private boolean duplicateCarName(String carName) {
        return cars.stream().anyMatch((car1) -> car1.getName().equals(carName));
    }
}
