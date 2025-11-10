package racingcar.util;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.model.Car;

public class InputParserTest {
    @Test
    public void 정상_입력_자동차_이름들이_들어왔을_경우() {
        //given
        String input = "pobi,woni,jun";

        //when
        String[] output = InputParser.parseCarNames(input);

        //then
        Assertions.assertArrayEquals(output, new String[]{"pobi", "woni", "jun"});
    }

    @Test
    public void 예외_자동차_이름이_중복됐을_경우() {
        //given
        String input = "pobi,pobi,jun";

        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseCarNames(input));
    }

    @Test
    public void 예외_자동차_이름이_5자리_이상인_경우() {
        // given
        String input = "pobippobi,jun";

        // when

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseCarNames(input));
    }

    @Test
    public void 정상_입력_시도_횟수() {
        //given
        String input = "3";

        //when
        int num = InputParser.parseInt(input);

        //then
        Assertions.assertEquals(num, 3);
    }

    @Test
    public void 예외_시도_횟수가_정수가_아닐_경우() {
        //given
        String input = "aa  as";

        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseInt(input));
    }

    @Test
    public void 예외_시도_횟수가_0_이하인_경우() {
        // given
        String input = "0";

        // when

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseInt(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DEFAULT, 10, 100, 10", "TRUCK, -10, 50, 100", "TROLL, -100, 5, 10000"})
    public void 정상_커스텀_자동차_추가(String input) {
        // given
        String[] inputs = Arrays.stream(input.split(",")).map(String::trim).toArray(String[]::new);

        // when
        Car car = InputParser.parseCar(input);

        // then
        Assertions.assertEquals(car.getCarSpec().name(), inputs[0]);
        Assertions.assertEquals(car.getSpeed(), Integer.parseInt(inputs[1]));
        Assertions.assertEquals(car.getPower(), Integer.parseInt(inputs[2]));
        Assertions.assertEquals(car.getMaxLuck(), Integer.parseInt(inputs[3]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DEAULT, 10, 100, 10", "DEㄷFAULT,10, 100, 10", "aaa, -10, 50, 100",
            "TROLL, -1000, 50, 100"})
    public void 예외_커스텀_자동차_타입이_없는_경우(String input) {
        // given

        // when

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputParser.parseCar(input));
    }

}
