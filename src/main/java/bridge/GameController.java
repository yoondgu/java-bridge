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
    private BridgeGame bridgeGame;

    public void run() {
        System.out.println("다리 건너기 게임을 시작합니다.\n");
        System.out.println("다리의 길이를 입력해주세요.");
        int bridgeSize = askBridgeSize();
        bridgeGame = new BridgeGame(bridgeSize);
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
                System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
                boolean toQuit = !askGameCommand();
                if (toQuit) {
                    break;
                }
                bridgeGame.retry();
            }
        }

        System.out.println("최종 게임 결과");
        outputView.printMap(movingMap);
        outputView.printResult(bridgeGame.hasPlayerFailed(), bridgeGame.getTrialCount());
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

    private boolean askGameCommand() {
        try {
            return inputView.readGameCommand();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return askGameCommand();
        }
    }
}
