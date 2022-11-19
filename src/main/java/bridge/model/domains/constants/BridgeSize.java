package bridge.model.domains.constants;

public enum BridgeSize {

    MINIMUM(3), MAXIMUM(20);

    private final int value;

    BridgeSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
