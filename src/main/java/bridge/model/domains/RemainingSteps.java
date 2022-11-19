package bridge.model.domains;

import bridge.model.domains.constants.BridgeSize;
import bridge.model.domains.constants.Step;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RemainingSteps {

    private final Queue<String> steps = new LinkedList<>();

    public RemainingSteps(List<String> bridge) {
        validate(bridge);
        initializeSteps(bridge);
    }

    private void initializeSteps(List<String> bridge) {
        this.steps.addAll(bridge);
    }

    private void validate(List<String> bridge) {
        // TODO 예외 유형 검토, 메시지 작성
        if (bridge == null) {
            throw new IllegalArgumentException();
        }
        if (hasOddSize(bridge)) {
            throw new IllegalArgumentException();
        }
        if (hasOddStep(bridge)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasOddSize(List<String> bridge) {
        int size = bridge.size();
        return size < BridgeSize.MINIMUM.getValue() || size > BridgeSize.MAXIMUM.getValue();
    }

    private boolean hasOddStep(List<String> bridge) {
        for (String step : bridge) {
            if (step == null) {
                return true;
            }
            if (notEqualsWithAnyKeyword(step)) {
                return true;
            }
        }
        return false;
    }

    private boolean notEqualsWithAnyKeyword(String step) {
        if (step.equals(Step.UP.getKeyword())) {
            return false;
        }
        return !step.equals(Step.DOWN.getKeyword());
    }

    public boolean isMovableStep(String step) {
        String movableStep = steps.poll();
        // TODO 예외 처리 위치 및 유형 검토, 메시지 작성
        if (movableStep == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return movableStep.equals(step);
    }

    public boolean isEmpty() {
        return steps.isEmpty();
    }
}
