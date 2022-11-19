package bridge.model.domains.constants;

public enum Step {

    UP("U"),
    DOWN("D");

    private final String keyword;

    Step(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
