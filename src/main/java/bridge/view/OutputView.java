package bridge.view;

import bridge.view.constants.OutputFormat;
import bridge.view.constants.OutputMessage;
import bridge.view.constants.ResultKeyword;
import bridge.view.template.MovingMap;
import bridge.view.utils.ConsolePrinter;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 클래스
 */
public class OutputView {

    private final ConsolePrinter consolePrinter;

    public OutputView() {
        this.consolePrinter = new ConsolePrinter();
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     */
    public void printMap(MovingMap movingMap) {
        consolePrinter.printLine(movingMap + "\n");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     */
    public void printResult(boolean hasFailed, int trialCount) {
        consolePrinter.printFormattedLine(OutputFormat.RESULT_HAS_SUCCEED, ResultKeyword.convertValueByKey(!hasFailed));
        consolePrinter.printFormattedLine(OutputFormat.RESULT_TRIAL_COUNT, trialCount);
    }

    public void printMessage(OutputMessage message) {
        consolePrinter.printLine(message.getValue());
    }

    public void printGameErrorMessage(String errorMessage) {
        consolePrinter.printFormattedLine(OutputFormat.ERROR_GAME_MESSAGE, errorMessage);
    }

    public void printUserInputErrorMessage(String errorMessage) {
        consolePrinter.printFormattedLine(OutputFormat.ERROR_USER_INPUT_MESSAGE, errorMessage);
    }
}
