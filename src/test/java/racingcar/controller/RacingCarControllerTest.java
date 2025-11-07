package racingcar.controller;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.model.RacingCar;

public class RacingCarControllerTest {
    @Test
    public void 우승자_구하기() {
        // given
        RacingCarController racingCarController = new RacingCarController();
        RacingCar racingCar1 = new RacingCar("aa");
        RacingCar racingCar2 = new RacingCar("aa");
//        racingCar1.incrementStatus();
        
        racingCarController.racingCars.add(racingCar1);
        racingCarController.racingCars.add(racingCar2);

        // when
        ArrayList<String> result = racingCarController.getWinners();

        // then
        Assertions.assertEquals(2, result.size());
    }
}
