package bridge.view.constants;

public enum MovingMapDisplay {

    SUCCESS("O"), FAIL("X"),
    NOTHING(" ")
    ;

    private final String display;

    MovingMapDisplay(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
