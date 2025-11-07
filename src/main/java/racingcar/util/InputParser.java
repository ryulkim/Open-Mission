package racingcar.util;

import static racingcar.common.Exception.NOT_NUMBER;
import static racingcar.common.Exception.NOT_UNDER_ZERO;

import java.util.HashSet;
import java.util.Set;
import racingcar.common.Exception;

public class InputParser {

    static final int MINIMUM_CARS_NAME_LENGTH = 5;

    public static String[] parseCarNames(String input) {
        String[] names = input.split(",");
        Set<String> uniqueNames = new HashSet<>();

        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].trim();

            if (!uniqueNames.add(names[i])) {
                throw new IllegalArgumentException(Exception.DUPLICATE_CAR_NAME + ": " + names[i]);
            }
            if (!validLess(names[i])) {
                throw new IllegalArgumentException(Exception.EXCEED_CAR_NAME.toString());
            }
        }
        return names;
    }

    public static int parseInt(String input) {
        try {
            int result = Integer.parseInt(input);
            if (result <= 0) {
                throw new IllegalArgumentException(NOT_UNDER_ZERO.toString());
            }
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER.toString());
        }
    }

    private static boolean validLess(String input) {
        return input.length() <= MINIMUM_CARS_NAME_LENGTH;
    }
}
