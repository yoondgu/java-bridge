package bridge.model.domains.constants;

public enum Step {

    UP("U"), DOWN("D");

    private final String value;

    Step(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
