package racingcar.common;

public enum Exception {
    EXCEED_CAR_NAME("자동차 이름은 5자리 이하여야 합니다."),
    DUPLICATE_CAR_NAME("중복된 자동차 이름이 있습니다"),
    NOT_UNDER_ZERO("시도 횟수가 0 이하일 수는 없습니다."),
    NOT_NUMBER("정수가 아닌 입력값을 받았습니다.");
    private final String message;

    Exception(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
