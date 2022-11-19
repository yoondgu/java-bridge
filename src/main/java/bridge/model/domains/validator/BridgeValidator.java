package bridge.model.domains.validator;

import bridge.model.domains.constants.BridgeSize;
import bridge.model.domains.constants.Step;

import java.util.List;

public class BridgeValidator {

    public static void validateBridge(List<String> bridge) {
        if (bridge == null) {
            throw new IllegalArgumentException();
        }
        validateBridgeSize(bridge.size());
        validateBridgeSteps(bridge);
    }

    public static void validateBridgeSize(int size) {
        if (isOddBridgeSize(size)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isOddBridgeSize(int size) {
        return size < BridgeSize.MINIMUM.getValue() || size > BridgeSize.MAXIMUM.getValue();
    }

    public static void validateBridgeSteps(List<String> bridge) {
        for (String step : bridge) {
            validateStep(step);
        }
    }

    public static void validateStep(String step) {
        if (isOddStep(step)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isOddStep(String step) {
        if (step == null) {
            return true;
        }
        return notEqualsWithAnyKeyword(step);
    }

    private static boolean notEqualsWithAnyKeyword(String step) {
        if (step.equals(Step.UP.getKeyword())) {
            return false;
        }
        return !step.equals(Step.DOWN.getKeyword());
    }
}
