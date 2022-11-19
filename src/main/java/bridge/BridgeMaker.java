package bridge;

import bridge.model.domains.constants.BridgeSize;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validateSize(size);
        List<String> bridge = new ArrayList<>();
        while (bridge.size() < size) {
            addStepKeywordToBridge(bridge);
        }
        return bridge;
    }

    private void addStepKeywordToBridge(List<String> bridge) {
        int bridgeNumber = bridgeNumberGenerator.generate();
        String step = ExpressionConverter.STEP.convert(bridgeNumber);
        validateStep(step);
        bridge.add(step);
    }

    private void validateSize(int size) {
        if (size < BridgeSize.MINIMUM.getValue() || size > BridgeSize.MAXIMUM.getValue()) {
            // TODO 클래스 분리 검토, 예외 메시지 작성
            throw new IllegalArgumentException();
        }
    }

    private void validateStep(String step) {
        if (step == null) {
            // TODO 예외 발생 위치, 예외 종류 및 메시지 검토
            throw new IllegalArgumentException();
        }
    }
}
