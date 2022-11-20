package bridge.view.constants;

public enum MovingMapFormat {

    PREFIX("[ "),
    DELIMITER(" | "),
    SUFFIX(" ]")
    ;

    private final String value;

    MovingMapFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
