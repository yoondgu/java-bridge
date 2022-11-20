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

    public boolean isMovableStep(String moving) {
        String movableStep = steps.poll();
        validateStatus(movableStep);
        return movableStep.equals(moving);
    }

    private void validateStatus(String polledValue) {
        if (polledValue == null) {
            throw new IllegalStateException("기능 오류: 더 이상 건너야 할 칸 정보가 없습니다.");
        }
    }

    public boolean isEmpty() {
        return steps.isEmpty();
    }
}
