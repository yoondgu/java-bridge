package bridge.model.domains;

public enum Step {

    UP("U", 1),
    DOWN("D", 0);

    private final String keyword;
    private final int value;

    Step(String keyword, int value) {
        this.keyword = keyword;
        this.value = value;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getValue() {
        return value;
    }
}
