package racingcar.common;

public class Message {
    public static final String INPUT_NUM = "시도할 횟수는 몇 회인가요?(최대 20)";
    public static final String INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    public static final String INPUT_SELECT_CAR = "어떤 자동차가 이길 것 같나요?";
    public static final String RESULT = "실행 결과";
    public static final String WINNER = "최종 우승자";
    public static final String SELECT_MODE = "모드를 선택하세요.\n1. 혼자하는 경마 게임\n2. 멀티 플레이 경마게임 (업데이트 예정)";
    public static final String SELECT_SINGLE_MODE = "모드를 선택하세요.\n1. 대결\n2. 현재 자동차(경마) 리스트\n3. 커스텀 자동차 추가\n4. 종료";
    public static final String CAR_TYPE = """
            추가할 수 있는 자동차 종류
            
            1. 기본 카(DEFAULT)
            \t- 속도 (0~100)
            \t- 파워 (최대 100)
            \t- 운빨 최대치 (최대 100까지)
            2. 트롤 카(TROLL)
            \t- 속도 (-100~5)
            \t- 파워 (최대 100)
            \t- 운빨 최대치 (최대 10000까지)
            3. 트럭(TRUCK)
            \t- 속도 (-10~50)
            \t- 파워 (최대 10000)
            \t- 운빨 최대치 (최대 200까지)
            
            """;
    public static final String CUSTOM_CAR = "추가하실 경우, 다음과 같이 입력해주세요.\nex) DEFAULT, 10, 100, 10\n예시대로 할 경우, 기본 카로 속도 10, 파워 100, 운빨 100인 스펙의 자동차가 만들어지게 됩니다.\n";
    public static final String CUR_CARS = "현재 자동자 리스트\n";

}
