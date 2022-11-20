package bridge.view.constants;

public enum OutputFormat {

    RESULT_HAS_SUCCEED("게임 성공 여부: %s\n"),
    RESULT_TRIAL_COUNT("총 시도한 횟수: %d\n"),

    ERROR_MESSAGE("[ERROR] %s");

    private final String value;

    OutputFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
