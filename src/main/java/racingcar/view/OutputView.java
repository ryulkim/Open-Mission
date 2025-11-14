package racingcar.view;

import static racingcar.common.Message.CUR_CARS;
import static racingcar.common.Message.WINNER;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.RacingCar;
import racingcar.util.Print;

public class OutputView {
    public static void finalWinner(String racingCar, String selectCar) {
        Print.println(String.format("%s : %s", WINNER, racingCar));
        Print.println(resultSelect(racingCar, selectCar));
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
        String output = String.format("%s : %s", racingCar.getName(), racingCar.getStatus());
        Print.println(output);
    }

    private static String carInfo(Car car) {
        return String.format("%s(type: %s, speed: %d, power: %d, luck: %d)", car.getCarSpec().name(),
                car.getCarSpec().name(), car.getSpeed(), car.getPower(), car.getMaxLuck());
    }

    private static String resultSelect(String racingCar, String selectCar) {
        if (racingCar.equals(selectCar)) {
            return "You Win!!";
        }
        return "You lose";
    }
}
