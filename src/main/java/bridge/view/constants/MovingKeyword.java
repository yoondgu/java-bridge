package bridge.view.constants;

public enum MovingKeyword {
    UP("U", "U"), DOWN("D", "D"),
    ;

    private final String key;
    private final String value;

    MovingKeyword(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
