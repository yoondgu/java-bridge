package bridge;

import bridge.model.BridgeGame;
import bridge.view.InputView;
import bridge.view.MovingMap;
import bridge.view.OutputView;
import bridge.view.utils.MovingMapGenerator;

import java.util.List;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        System.out.println("다리의 길이를 입력해주세요.");
        int bridgeSize = askBridgeSize();
        BridgeGame bridgeGame = new BridgeGame(bridgeSize);
        MovingMap movingMap = null;

        while (!bridgeGame.hasAllMovingDone()) {
            System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
            String moving = askMoving();
            bridgeGame.move(moving);
            boolean hasFailed = bridgeGame.hasPlayerFailed();
            List<String> movingHistory = bridgeGame.getPlayerMovingHistory();
            movingMap = new MovingMap(new MovingMapGenerator(movingHistory, hasFailed));
            outputView.printMap(movingMap);
            if (hasFailed) {
                // TODO 재시도/종료 여부에 따라 bridgeGame.retry() / stopMoving = true;
                // TODO 재시도/종료 여부 입력받기
                boolean toQuit = false;
                if (toQuit) {
                    break;
                }
                bridgeGame.retry();
            }
        }
        // TODO 최종 결과 출력하기
        System.out.println("최종 게임 결과");
        outputView.printMap(movingMap);
    }

    private int askBridgeSize() {
        try {
            return inputView.readBridgeSize();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return askBridgeSize();
        }
    }

    private String askMoving() {
        try {
            return inputView.readMoving();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return askMoving();
        }
    }
}
