package bridge.view;

import bridge.view.constants.OutputFormat;
import bridge.view.constants.OutputMessage;

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
        System.out.println(movingMap + "\n");
    }


    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(boolean hasFailed, int trialCount) {
        validateTrialCount(trialCount);
        System.out.printf(OutputFormat.RESULT_HAS_SUCCEED.getValue(), getResultWord(hasFailed));
        System.out.printf(OutputFormat.RESULT_TRIAL_COUNT.getValue(), trialCount);
    }

    // TODO 변환 로직 구현 위치 검토
    private String getResultWord(boolean hasFailed) {
        if (hasFailed) {
            return "실패";
        }
        return "성공";
    }

    // TODO 검증 로직 구현 위치 검토
    private void validateTrialCount(int trialCount) {
        if (trialCount < 1) {
            throw new IllegalArgumentException("기능 오류: 시도 횟수가 잘못되었습니다.");
        }
    }

    public void printMessage(OutputMessage message) {
        validateMessage(message);
        System.out.println(message.getValue());
    }

    public void printErrorMessage(String errorMessage) {
        validateMessage(errorMessage);
        System.out.printf(OutputFormat.ERROR_MESSAGE.getValue(), errorMessage);
    }

    private void validateMessage(Object message) {
        if (message == null) {
            throw new NullPointerException("기능 오류: 출력 메세지의 값이 null입니다.");
        }
    }
}
