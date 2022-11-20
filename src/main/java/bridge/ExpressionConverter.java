package bridge;

import bridge.model.domains.constants.Step;
import bridge.view.constants.Result;

import java.util.function.Function;

public enum ExpressionConverter {

    STEP(ExpressionConverter::convertBridgeNumberToStep),
    RESULT(ExpressionConverter::convertBooleanToResult);

    private final Function<Object, String> function;

    ExpressionConverter(Function<Object, String> function) {
        this.function = function;
    }

    public String convert(Object determinant) {
        return this.function.apply(determinant);
    }

    private static String convertBridgeNumberToStep(Object determinant) {
        int bridgeNumber = Integer.parseInt(determinant.toString());
        if (bridgeNumber == 1) {
            return Step.UP.getValue();
        }
        if (bridgeNumber == 0) {
            return Step.DOWN.getValue();
        }
        return null;
    }

    private static String convertBooleanToResult(Object determinant) {
        boolean hasSucceed = (boolean) determinant;
        if (hasSucceed) {
            return Result.SUCCESS.getKeyword();
        }
        return Result.FAIL.getKeyword();
    }
}
