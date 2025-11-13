package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.common.CarSpec;
import racingcar.model.Car;
import racingcar.model.CarFactory;
import racingcar.model.RacingCar;

public class RacingCarService {
    List<RacingCar> racingCars;
    List<Car> cars;

    public RacingCarService() {
        racingCars = new ArrayList<>();
        cars = new ArrayList<>();
        cars.add(CarFactory.createCar(CarSpec.DEFAULT, 10, 100, 10));
        cars.add(CarFactory.createCar(CarSpec.TRUCK, 10, 1000, 10));
        cars.add(CarFactory.createCar(CarSpec.TROLL, -5, 10, 500));
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
        cars.stream().map(RacingCar::createCar).forEach(racingCars::add);
    }

    public void addCustomCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void racingCarClear() {
        racingCars.clear();
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
            return Randoms.pickNumberInRange(car.getSpeed(), -car.getSpeed()) * Randoms.pickNumberInRange(0,
                    car.getMaxLuck());
        }
        return car.getSpeed() * Randoms.pickNumberInRange(0, car.getMaxLuck());
    }
}
