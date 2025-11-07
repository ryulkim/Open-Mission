package racingcar.util;

import racingcar.model.RacingCar;

public class OutputView {
    public static void print(String message) {
        System.out.println(message);
    }

    public static void printCarStatus(RacingCar racingCar) {
        String output = String.format("%s : %s", racingCar.getName(),
                "-".repeat(racingCar.getStatus()));
        print(output);
    }
}
