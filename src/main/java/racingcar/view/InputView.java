package racingcar.view;

import static racingcar.common.Message.INPUT_CAR_NAME;
import static racingcar.common.Message.INPUT_NUM;
import static racingcar.common.Message.SELECT_MODE;
import static racingcar.common.Message.SELECT_SINGLE_MODE;

import camp.nextstep.edu.missionutils.Console;
import racingcar.util.InputParser;
import racingcar.util.Print;

public class InputView {

    public static int inputMode() {
        Print.println(SELECT_MODE);
        return InputParser.parseInt(readLine());
    }

    public static int inputSingleMode() {
        Print.println(SELECT_SINGLE_MODE);
        return InputParser.parseInt(readLine());
    }

    public static int inputNum() {
        Print.println(INPUT_NUM);
        return InputParser.parseInt(readLine());
    }

    public static String[] inputCarNames() {
        Print.println(INPUT_CAR_NAME);
        return InputParser.parseCarNames(readLine());
    }

    public static void close() {
        Console.close();
    }

    private static String readLine() {
        return Console.readLine();
    }

}
