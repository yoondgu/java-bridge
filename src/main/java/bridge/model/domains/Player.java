package bridge.model.domains;

public class Player {

    private final RemainingSteps remainingSteps;
    private boolean isFailed = false;

    public Player(RemainingSteps steps) {
        this.remainingSteps = steps;
    }

    public void addOneMoving(String moving) {
        validateStatus();
        isFailed = !remainingSteps.isMovableStep(moving);
    }

    public boolean hasAllMovingDone() {
        return remainingSteps.isEmpty();
    }

    public boolean isFailed() {
        return isFailed;
    }

    private void validateStatus() {
        if (remainingSteps.isEmpty()) {
            throw new IllegalStateException("기능 오류: 사용자가 이미 다리를 모두 건넜습니다.");
        }
        if (isFailed) {
            throw new IllegalStateException("기능 오류: 이미 다리 건너기를 실패했습니다.");
        }
    }
}
