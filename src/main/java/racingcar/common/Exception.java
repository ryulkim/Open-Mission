package racingcar.common;

public enum Exception {
    EXCEED_CAR_NAME("자동차 이름은 5자리 이하여야 합니다."),
    DUPLICATE_CAR_NAME("중복된 자동차 이름이 있습니다"),
    NOT_UNDER_ZERO("시도 횟수가 1~20 사이여야 합니다."),
    NOT_NUMBER("정수가 아닌 입력값을 받았습니다."),
    EXCEED_CAR_SPEED("스피드 범위에 맞지 않습니다."),
    EXCEED_CAR_POWER("파워 범위에 맞지 않습니다."),
    EXCEED_CAR_LUCK("최대 운빨 범위에 맞지 않습니다."),
    NO_CAR_TYPE("없는 차 종류입니다."),
    NOT_MODE("없는 모드입니다. 1~2 중에 선택해주세요."),
    NOT_SINGLE_MODE("없는 모드입니다. 1~3 중에 선택해주세요."),
    ;
    private final String message;

    Exception(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
