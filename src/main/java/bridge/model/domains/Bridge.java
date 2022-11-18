package bridge.model.domains;

import java.util.List;

public class Bridge {

    private final List<String> bridge;

    public Bridge(List<String> bridge) {
        validate(bridge);
        this.bridge = bridge;
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
        // TODO 최솟값 최댓값 상수 처리
        int size = bridge.size();
        return size < 3 || size > 20;
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
}
