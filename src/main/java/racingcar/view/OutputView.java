package racingcar.view;

import static racingcar.common.Message.CUR_CARS;
import static racingcar.common.Message.WINNER;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.RacingCar;
import racingcar.util.Print;

public class OutputView {
    public static void finalWinner(List<String> racingCars) {
        Print.println(String.format("%s : %s", WINNER, String.join(", ", racingCars)));
        Print.println("");
    }


    public static void printCars(List<Car> cars) {
        Print.println(CUR_CARS);
        for (int i = 0; i < cars.size(); i++) {
            Print.println(String.format("%d. %s", i + 1, carInfo(cars.get(i))));
        }
        Print.println("");
    }

    public static void printCarStatus(RacingCar racingCar) {
        String output = String.format("%s : %s", racingCar.getName(),
                "-".repeat(racingCar.getStatus()));
        Print.println(output);
        Print.println("");
    }

    private static String carInfo(Car car) {
        return String.format("%s(type: %s, speed: %d, power: %d, luck: %d)", car.getCarSpec().name(),
                car.getCarSpec().name(), car.getSpeed(), car.getPower(), car.getMaxLuck());
    }


}
