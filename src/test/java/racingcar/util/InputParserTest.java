package racingcar.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}
