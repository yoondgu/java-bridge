package bridge;

import bridge.model.BridgeGame;
import bridge.model.domains.Player;
import bridge.view.InputView;
import bridge.view.OutputView;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        System.out.println("다리의 길이를 입력해주세요.");
        int bridgeSize = askBridgeSize();
        BridgeGame bridgeGame = new BridgeGame(bridgeSize);

        boolean stopMoving = false;
        while (stopMoving) {
            // TODO 칸 입력받기
            // TODO 잘못된 값이면 다시 입력받기
            Player player = bridgeGame.move("U");
            // TODO 결과 출력하기
            if (player.hasDoneMoving()) {
                stopMoving = true;
            }
            if (!player.hasSurvived()) {
                // TODO 재시도/종료 여부에 따라 bridgeGame.retry() / stopMoving = true;
                // TODO 재시도/종료 여부 입력받기
                String retryOrQuit = "R";
                if ("R".equals(retryOrQuit)) {
                    bridgeGame.retry();
                }
                if ("Q".equals(retryOrQuit)) {
                    stopMoving = true;
                }
            }
        }

        // TODO 최종 결과 출력하기
    }

    private int askBridgeSize() {
        try {
            return inputView.readBridgeSize();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return askBridgeSize();
        }
    }
}
