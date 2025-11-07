package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import racingcar.model.RacingCar;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {
    ArrayList<RacingCar> racingCars;

    public RacingCarController() {
        racingCars = new ArrayList<>();
    }

    public void run() {
        initRacingCars(InputView.inputCarNames());
        OutputView.printGameResult(InputView.inputNum(), racingCars);

        ArrayList<String> winners = getWinners();
        OutputView.finalWinner(winners);
        InputView.close();
    }

    public ArrayList<String> getWinners() {
        AtomicInteger max = new AtomicInteger();
        ArrayList<String> winners = new ArrayList<>();

        racingCars.sort((a, b) -> Integer.compare(b.getStatus(), a.getStatus()));

        racingCars.forEach((racingCar -> {
            if (max.get() <= racingCar.getStatus()) {
                max.set(racingCar.getStatus());
                winners.add(racingCar.getName());
            }
        }));

        return winners;
    }

    private void initRacingCars(String[] carNames) {
        Arrays.stream(carNames).map(RacingCar::createCar).forEach(racingCars::add);
    }

}
