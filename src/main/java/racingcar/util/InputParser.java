package racingcar.util;

import static racingcar.common.Exception.NOT_NUMBER;
import static racingcar.common.Exception.NOT_UNDER_ZERO;
import static racingcar.common.Exception.DUPLICATE_CAR_NAME;
import static racingcar.common.Exception.EXCEED_CAR_NAME;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InputParser {

    static final int MAXIMUM_CAR_NAME_LENGTH = 5;

    public static String[] parseCarNames(String input) {
        String[] names = input.split(",");
        Set<String> uniqueNames = new HashSet<>();

        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].trim();

            if (!uniqueNames.add(names[i])) {
                throw new IllegalArgumentException(DUPLICATE_CAR_NAME + ": " + names[i]);
            }
            if (names[i].length() > MAXIMUM_CAR_NAME_LENGTH) {
                throw new IllegalArgumentException(EXCEED_CAR_NAME.toString());
            }
        }
        return names;
    }

    public static int parseInt(String input) {
        int result;
        try {
            result = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER.toString());
        }
        if (result <= 0) {
            throw new IllegalArgumentException(NOT_UNDER_ZERO.toString());
        }
        return result;
    }
}
