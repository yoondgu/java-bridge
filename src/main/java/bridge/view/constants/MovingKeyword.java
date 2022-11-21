package bridge.view.constants;

public enum MovingKeyword implements Keyword {

    UP("U", "U"), DOWN("D", "D");

    private final String key;
    private final String value;

    MovingKeyword(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getValue() {
        return value;
    }
}
