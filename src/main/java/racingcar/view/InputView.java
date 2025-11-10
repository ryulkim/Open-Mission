package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.util.InputParser;
import racingcar.util.Print;

public class InputView {

    public static int inputNum() {
        Print.println("시도할 횟수는 몇 회인가요?");
        return InputParser.parseInt(readLine());
    }

    public static String[] inputCarNames() {
        Print.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return InputParser.parseCarNames(readLine());
    }

    public static void close() {
        Console.close();
    }

    private static String readLine() {
        return Console.readLine();
    }

}
