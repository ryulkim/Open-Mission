package racingcar.view;

import static racingcar.common.Message.CUR_CARS;
import static racingcar.common.Message.RESULT;
import static racingcar.common.Message.WINNER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import racingcar.model.Car;
import racingcar.model.RacingCar;

public class OutputView {
    public static void finalWinner(ArrayList<String> racingCars) {
        print(String.format("%s : %s", WINNER, String.join(", ", racingCars)));
    }

    public static void printGameResult(int number, ArrayList<RacingCar> racingCars) {
        print(RESULT);
        for (int i = 0; i < number; i++) {
            round(racingCars);
            racingCars.forEach((OutputView::printCarStatus));
            print("");
        }
    }

    public static void printCars(ArrayList<Car> cars) {
        print(CUR_CARS);
        for (int i = 0; i < cars.size(); i++) {
            print(String.format("%d. %s", i + 1, carInfo(cars.get(i))));
        }
    }

    private static String carInfo(Car car) {
        return String.format("%s(type: %s, speed: %d, power: %d, luck: %d)", car.getCarSpec().name(),
                car.getCarSpec().name(), car.getSpeed(), car.getPower(), car.getMaxLuck());
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
                racingCar.move();
            }
        });
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
