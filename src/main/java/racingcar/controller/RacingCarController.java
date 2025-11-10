package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import racingcar.common.CarSpec;
import racingcar.model.Car;
import racingcar.model.CarFactory;
import racingcar.model.RacingCar;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {
    ArrayList<RacingCar> racingCars;
    ArrayList<Car> cars;

    public RacingCarController() {
        racingCars = new ArrayList<>();
        cars = new ArrayList<>();
        cars.add(CarFactory.createCar(CarSpec.DEFAULT, 10, 100, 10));
        cars.add(CarFactory.createCar(CarSpec.TRUCK, 10, 1000, 10));
        cars.add(CarFactory.createCar(CarSpec.TROLL, -5, 10, 1000));
    }

    public void run() {
        racingCars.clear();
//        InputView.inputMode();
//        InputView.inputSingleMode();
        cars.add(InputView.inputCustomCar());
//        OutputView.printGameResult(InputView.inputNum(), racingCars);
        OutputView.printCars(cars);
//        ArrayList<String> winners = getWinners();
//        OutputView.finalWinner(winners);
        InputView.close();
    }

    public ArrayList<String> getWinners() {
        int maxStatus = getMaxStatus();
        ArrayList<String> winners = new ArrayList<>();

        for (RacingCar racingCar : racingCars) {
            if (racingCar.getStatus() == maxStatus) {
                winners.add(racingCar.getName());
            }
        }

        return winners;
    }

    private int getMaxStatus() {
        return racingCars.stream().mapToInt(RacingCar::getStatus).max().orElse(0);
    }

    private void initRacingCars(String[] carNames) {
        Arrays.stream(carNames).map(RacingCar::createCar).forEach(racingCars::add);
    }

}
