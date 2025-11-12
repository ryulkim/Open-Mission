package racingcar.controller;

import static racingcar.common.Message.RESULT;

import java.util.List;
import racingcar.model.RacingCar;
import racingcar.service.RacingCarService;
import racingcar.util.Print;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    public void run() {
        selectMode();
        InputView.close();
    }

    private void selectMode() {
        int mode = InputView.inputMode();
        if (mode == 1) {
            selectSingleMode();
        }
    }

    private void selectSingleMode() {
        while (true) {
            int singleMode = InputView.inputSingleMode();
            if (singleMode == 1) {
                int round = InputView.inputNum();
                List<String> winners = game(round);
                OutputView.finalWinner(winners);
                racingCarService.racingCarClear();
            } else if (singleMode == 2) {
                OutputView.printCars(racingCarService.getCars());
            } else if (singleMode == 3) {
                racingCarService.addCustomCar(InputView.inputCustomCar());
                OutputView.printCars(racingCarService.getCars());
            } else if (singleMode == 4) {
                return;
            }
        }
    }

    public List<String> game(int round) {
        racingCarService.initRacingCars();
        Print.println(RESULT);
        for (int i = 0; i < round; i++) {
            List<RacingCar> curRacingCars = racingCarService.round();
            curRacingCars.forEach((OutputView::printCarStatus));
            Print.println("");
        }
        return racingCarService.getWinners();
    }


}
