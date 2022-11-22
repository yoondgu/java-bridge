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

    public static String convert(int bridgeNumber) {
        if (bridgeNumber == 1) {
            return UP.getValue();
        }
        if (bridgeNumber == 0) {
            return DOWN.getValue();
        }
        return null;
    }
}
