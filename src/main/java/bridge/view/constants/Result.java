package bridge.view.constants;

public enum Result {

    SUCCESS("O"), FAIL("X")
    ;

    private final String keyword;

    Result(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
