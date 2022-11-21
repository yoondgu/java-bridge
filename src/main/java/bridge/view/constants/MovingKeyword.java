package bridge.view.constants;

import bridge.model.domains.constants.Step;

public enum MovingKeyword implements Keyword {

    UP("U", Step.UP.getValue()), DOWN("D", Step.DOWN.getValue());

    private final String key;
    private final String value;

    MovingKeyword(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getValue() {
        return value;
    }
}
