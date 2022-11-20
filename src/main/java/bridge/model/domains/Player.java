package bridge.model.domains;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final RemainingSteps remainingSteps; // TODO 외부 클래스로 바꾸고 Player에서는 현재 위치 int만 관리?
    private final List<String> movingHistory;

    private boolean hasSurvived = true;

    public Player(List<String> bridge) {
        this.remainingSteps = new RemainingSteps(bridge);
        this.movingHistory = new ArrayList<>();
    }

    public void addOneMoving(String moving) {
        validateStatus();
        movingHistory.add(moving);
        hasSurvived = remainingSteps.isMovableStep(moving);
    }

    public boolean hasAllMovingDone() {
        return remainingSteps.isEmpty();
    }

    public boolean hasSurvived() {
        return hasSurvived;
    }

    private void validateStatus() {
        if (remainingSteps.isEmpty()) {
            throw new IllegalStateException("기능 오류: 사용자가 이미 다리를 모두 건넜습니다.");
        }
        if (!hasSurvived) {
            throw new IllegalStateException("기능 오류: 이미 다리 건너기를 실패했습니다.");
        }
    }
}
