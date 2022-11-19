package bridge.model.domains;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final RemainingSteps remainingSteps;
    private final List<String> movingHistory;

    private boolean hasSurvived = true;

    public Player(List<String> bridge) {
        this.remainingSteps = new RemainingSteps(bridge);
        this.movingHistory = new ArrayList<>();
    }

    public void addOneStep(String step) {
        validatePlayerStatus();
        movingHistory.add(step);
        hasSurvived = remainingSteps.isMovableStep(step);
    }

    public boolean hasSucceed() {
        return hasSurvived && remainingSteps.isEmpty();
    }

    public boolean hasSurvived() {
        return hasSurvived;
    }

    private void validatePlayerStatus() {
        // TODO 에러메시지 상수화
        if (remainingSteps.isEmpty()) {
            throw new IllegalStateException("이미 다리를 모두 건넜습니다.");
        }
        if (!hasSurvived) {
            throw new IllegalStateException("이미 다리 건너기를 실패했습니다.");
        }
    }
}
