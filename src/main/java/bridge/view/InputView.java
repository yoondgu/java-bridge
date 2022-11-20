package bridge.view;

import bridge.model.domains.constants.BridgeSize;
import bridge.view.utils.ConsoleReader;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {



    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() throws IllegalArgumentException {
        int size = ConsoleReader.readLineAsInteger();
        if (size < BridgeSize.MINIMUM.getValue() || size > BridgeSize.MAXIMUM.getValue()) {
            throw new IllegalArgumentException("입력 오류: 다리의 길이는 3 이상 20 이하만 허용됩니다.");
        }
        return size;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return null;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return null;
    }
}
