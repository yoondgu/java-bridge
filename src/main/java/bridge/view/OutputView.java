package bridge.view;

import bridge.view.constants.OutputFormat;
import bridge.view.constants.OutputMessage;
import bridge.view.utils.ConsolePrinter;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(MovingMap movingMap) {
        ConsolePrinter.printLine(movingMap + "\n");
    }


    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(boolean hasFailed, int trialCount) {
        ConsolePrinter.printFormattedLine(OutputFormat.RESULT_HAS_SUCCEED, getResultWord(hasFailed));
        ConsolePrinter.printFormattedLine(OutputFormat.RESULT_TRIAL_COUNT, trialCount);
    }

    // TODO 변환 로직 구현 위치 검토
    private String getResultWord(boolean hasFailed) {
        if (hasFailed) {
            return "실패";
        }
        return "성공";
    }

    public void printMessage(OutputMessage message) {
        ConsolePrinter.printLine(message.getValue());
    }

    public void printErrorMessage(String errorMessage) {
        ConsolePrinter.printFormattedLine(OutputFormat.ERROR_MESSAGE, errorMessage);
    }
}
