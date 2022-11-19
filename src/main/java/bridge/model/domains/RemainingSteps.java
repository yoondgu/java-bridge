package bridge.model.domains;

import bridge.model.domains.validator.BridgeValidator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RemainingSteps {

    private final Queue<String> steps = new LinkedList<>();

    public RemainingSteps(List<String> bridge) {
        BridgeValidator.validateBridge(bridge);
        initializeSteps(bridge);
    }

    private void initializeSteps(List<String> bridge) {
        this.steps.addAll(bridge);
    }

    public boolean isMovableStep(String step) {
        String movableStep = steps.poll();
        validateStatus(movableStep);
        return movableStep.equals(step);
    }

    private void validateStatus(String polledValue) {
        // TODO 메시지 작성
        if (polledValue == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean isEmpty() {
        return steps.isEmpty();
    }
}
