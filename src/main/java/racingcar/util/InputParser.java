package racingcar.util;

import java.util.HashSet;
import java.util.Set;

public class InputParser {
    public static String[] parseCarNames(String input) {
        String[] names = input.split(",");
        Set<String> uniqueNames = new HashSet<>();

        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].trim();

            if (!uniqueNames.add(names[i])) {
                throw new IllegalArgumentException("중복된 자동차 이름이 있습니다: " + names[i]);
            }
            if (!validLess(names[i], 5)) {
                throw new IllegalArgumentException("자동차 이름은 5자리 이하여야 합니다.");
            }
        }
        return names;
    }

    public static int parseInt(String input) {
        try {
            int result = Integer.parseInt(input);
            if (result <= 0) {
                throw new IllegalArgumentException("시도 횟수가 0 이하일 수는 없습니다.");
            }
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닌 입력값을 받았습니다.");
        }
    }

    private static boolean validLess(String input, int length) {
        return input.length() <= length;
    }
}
