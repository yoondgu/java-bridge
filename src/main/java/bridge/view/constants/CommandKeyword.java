package bridge.view.constants;

public enum CommandKeyword implements Keyword {

    RETRY("R", true), QUIT("Q", false);

    private final String key;
    private final boolean value;

    CommandKeyword(String key, boolean value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Boolean getValue() {
        return value;
    }
}
