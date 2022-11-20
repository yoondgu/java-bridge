package bridge.view;

import bridge.view.utils.ConsolePrinter;
import bridge.view.utils.MovingMapGenerator;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<String> movingHistory, boolean isFailed) {
        validateMovingHistory(movingHistory);
        List<String> map = new MovingMapGenerator(movingHistory, isFailed).generateMap();
        ConsolePrinter.printMapDisplay(map);
    }

    private void validateMovingHistory(List<String> movingHistory) {
        if (movingHistory == null) {
            throw new NullPointerException("기능 오류 : 이동 결과가 null입니다.");
        }
        if (movingHistory.isEmpty()) {
            throw new IllegalArgumentException("기능 오류 : 이동 결과에 저장된 정보가 없습니다.");
        }
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
    }
}
