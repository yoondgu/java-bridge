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

        boolean keepMoving = true;
        while (keepMoving) {
            System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
            String moving = askMoving();
            Player player = bridgeGame.move(moving);
            outputView.printMap(player.getMovingHistory(), player.isFailed());
            if (player.hasAllMovingDone()) {
                keepMoving = false;
            }
            if (player.isFailed()) {
                // TODO 재시도/종료 여부에 따라 bridgeGame.retry() / stopMoving = true;
                // TODO 재시도/종료 여부 입력받기
                String retryOrQuit = "R";
                if ("R".equals(retryOrQuit)) {
                    bridgeGame.retry();
                }
                if ("Q".equals(retryOrQuit)) {
                    keepMoving = false;
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

    private String askMoving() {
        try {
            return inputView.readMoving();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return askMoving();
        }
    }
}
