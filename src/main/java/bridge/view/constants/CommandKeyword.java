package bridge.view.constants;

public enum CommandKeyword {

    RETRY("R", true), QUIT("Q", false);

    private final String key;
    private final boolean value;

    CommandKeyword(String key, boolean value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public boolean getValue() {
        return value;
    }
}
