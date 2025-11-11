package racingcar.view;

import static racingcar.common.Exception.NOT_MODE;
import static racingcar.common.Exception.NOT_SINGLE_MODE;
import static racingcar.common.Exception.NOT_UNDER_ZERO;
import static racingcar.common.Message.CAR_TYPE;
import static racingcar.common.Message.CUSTOM_CAR;
import static racingcar.common.Message.INPUT_CAR_NAME;
import static racingcar.common.Message.INPUT_NUM;
import static racingcar.common.Message.SELECT_MODE;
import static racingcar.common.Message.SELECT_SINGLE_MODE;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import racingcar.model.Car;
import racingcar.util.InputParser;
import racingcar.util.Print;

public class InputView {

    private static final int modeFirst = 1;
    private static final int modeLast = 1;
    private static final int singleModeFirst = 1;
    private static final int singleModeLast = 4;
    private static final int roundNumMin = 1;
    private static final int roundNumMax = 20;

    public static int inputMode() {
        return retryOnException(() -> {
            Print.println(SELECT_MODE);
            return validMode(readLine());
        });
    }

    public static int inputSingleMode() {
        return retryOnException(() -> {
            Print.println(SELECT_SINGLE_MODE);
            return validSingleMode(readLine());
        });
    }

    public static int inputNum() {
        return retryOnException(() -> {
            Print.println(INPUT_NUM);
            return validNum(readLine());
        });
    }

    public static String[] inputCarNames() {
        return retryOnException(() -> {
            Print.println(INPUT_CAR_NAME);
            return InputParser.parseCarNames(readLine());
        });
    }

    public static Car inputCustomCar() {
        return retryOnException(() -> {
            Print.println(CAR_TYPE);
            Print.println(CUSTOM_CAR);
            return InputParser.parseCar(readLine());
        });
    }

    public static void close() {
        Console.close();
    }

    private static int validMode(String mod) {
        int num = InputParser.parseInt(mod);
        if (num < modeFirst || num > modeLast) {
            throw new IllegalArgumentException(NOT_MODE.toString());
        }
        return num;
    }

    private static int validSingleMode(String mod) {
        int num = InputParser.parseInt(mod);
        if (num < singleModeFirst || num > singleModeLast) {
            throw new IllegalArgumentException(NOT_SINGLE_MODE.toString());
        }
        return num;
    }

    private static int validNum(String num) {
        int number = InputParser.parseInt(num);
        if (number < roundNumMin || number > roundNumMax) {
            throw new IllegalArgumentException(NOT_UNDER_ZERO.toString());
        }
        return number;
    }

    private static String readLine() {
        return Console.readLine();
    }

    private static <T> T retryOnException(Supplier<T> method) {
        while (true) {
            try {
                return method.get();
            } catch (IllegalArgumentException e) {
                Print.println(e.getMessage());
                Print.println("");
            }
        }
    }

}
