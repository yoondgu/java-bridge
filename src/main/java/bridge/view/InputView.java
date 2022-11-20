package bridge.view;

import bridge.model.domains.constants.BridgeSize;
import bridge.view.constants.CommandKeyword;
import bridge.view.constants.MovingKeyword;
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
        // TODO 도메인 로직 관련 검증 시 도메인 정보 import하는 문제 해결할 것(inputView 생성 시 전달 받기?)
        if (size < BridgeSize.MINIMUM.getValue() || size > BridgeSize.MAXIMUM.getValue()) {
            throw new IllegalArgumentException("입력 오류: 다리의 길이는 3 이상 20 이하만 허용됩니다.");
        }
        return size;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return ConsoleReader.readLineAsKeyword(MovingKeyword.values());
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public boolean readGameCommand() {
        return ConsoleReader.readLineAsKeyword(CommandKeyword.values());
    }
}
