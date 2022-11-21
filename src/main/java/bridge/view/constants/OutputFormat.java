package bridge.view.constants;

public enum OutputFormat {

    RESULT_HAS_SUCCEED("게임 성공 여부: %s"),
    RESULT_TRIAL_COUNT("총 시도한 횟수: %d"),

    ERROR_MESSAGE("[ERROR] %s"),
    ERROR_USER_INPUT_MESSAGE("[ERROR] 사용자 입력 오류: %s"),
    ERROR_GAME_MESSAGE("[ERROR] 게임 기능 오류: %s"),
    ;

    private final String value;

    OutputFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
