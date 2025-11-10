package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import racingcar.model.RacingCar;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {
    ArrayList<RacingCar> racingCars;

    public RacingCarController() {
        racingCars = new ArrayList<>();
    }

    public void run() {
//        InputView.inputMode();
//        InputView.inputSingleMode();
        InputView.inputCustomCar();
        initRacingCars(InputView.inputCarNames());
        OutputView.printGameResult(InputView.inputNum(), racingCars);

        ArrayList<String> winners = getWinners();
        OutputView.finalWinner(winners);
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
