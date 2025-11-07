package racingcar.view;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import racingcar.model.RacingCar;

public class OutputView {
    public static void finalWinner(ArrayList<String> racingCars) {
        print(String.format("%s : %s", "최종 우승자", String.join(", ", racingCars)));
    }

    public static void printGameResult(int number, ArrayList<RacingCar> racingCars) {
        print("실행 결과");
        for (int i = 0; i < number; i++) {
            round(racingCars);
            racingCars.forEach((OutputView::printCarStatus));
            print("");
        }
    }

    private static void printCarStatus(RacingCar racingCar) {
        String output = String.format("%s : %s", racingCar.getName(),
                "-".repeat(racingCar.getStatus()));
        print(output);
    }

    private static void round(ArrayList<RacingCar> racingCars) {
        racingCars.forEach(racingCar -> {
            int num = Randoms.pickNumberInRange(0, 9);
            if (num >= 4) {
                racingCar.incrementStatus();
            }
        });
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
