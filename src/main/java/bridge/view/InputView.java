package bridge.view;

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
    public int readBridgeSize(int minimum, int maximum) throws IllegalArgumentException {
        return ConsoleReader.readLineAsIntegerInRange(minimum, maximum);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() throws IllegalArgumentException {
        return ConsoleReader.readLineAsKeyword(MovingKeyword.values());
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public boolean readGameCommand() throws IllegalArgumentException {
        return ConsoleReader.readLineAsKeyword(CommandKeyword.values());
    }
}
