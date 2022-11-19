package bridge;

import bridge.model.BridgeGame;
import bridge.model.domains.Player;

public class GameController {

    public void run() {
        // TODO 다리 길이 입력받기
        BridgeGame bridgeGame = new BridgeGame(3);

        boolean stopMoving = false;
        while (stopMoving) {
            // TODO 칸 입력받기
            Player player = bridgeGame.move("U");
            // TODO 결과 출력하기
            if (player.hasSucceed()) {
                stopMoving = true;
            }
            if (!player.hasSurvived()) {
                // TODO 재시도/종료 여부에 따라 bridgeGame.retry() / stopMoving = true;
            }
        }

        // TODO 최종 결과 출력하기
    }
}
