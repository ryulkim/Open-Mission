package racingcar.service;

import org.junit.jupiter.api.Test;

public class RacingCarServiceTest {
    @Test
    public void 자동차_이름_초기화() {
        // given
        RacingCarService racingCarService = new RacingCarService();
        racingCarService.initRacingCars();

        // when
        racingCarService.racingCarClear();

        // then
        assert racingCarService.racingCars.isEmpty();
    }

    @Test
    public void 자동차_추가() {
        // given
        RacingCarService racingCarService = new RacingCarService();
        racingCarService.initRacingCars();

        // when

    }
}
