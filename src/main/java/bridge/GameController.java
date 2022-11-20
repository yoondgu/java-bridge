package bridge;

import bridge.model.BridgeGame;
import bridge.view.InputView;
import bridge.view.MovingMap;
import bridge.view.OutputView;
import bridge.view.utils.MovingMapGenerator;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private BridgeGame bridgeGame;
    private MovingMap movingMap = null;

    public void run() {
        System.out.println("다리 건너기 게임을 시작합니다.\n");
        initializeBridgeGame();
        playRoundsUntilFailOrDone();
        showResult();
    }

    private void initializeBridgeGame() {
        System.out.println("다리의 길이를 입력해주세요.");
        int bridgeSize = askBridgeSize();
        bridgeGame = new BridgeGame(bridgeSize);
    }

    private void playRoundsUntilFailOrDone() {
        boolean hasFailed = playOneRound();
        if (hasFailed) {
            retryOrQuit();
            return;
        }
        if (bridgeGame.hasAllMovingDone()) {
            return;
        }
        playRoundsUntilFailOrDone();
    }

    private void retryOrQuit() {
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        if (askToRetry()) {
            bridgeGame.retry();
            playRoundsUntilFailOrDone();
        }
    }

    private boolean playOneRound() {
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
        bridgeGame.move(askMoving());
        boolean hasFailed = bridgeGame.hasPlayerFailed();
        // TODO generator 없이 MovingMap 객체 생성 검토
        MovingMapGenerator generator = new MovingMapGenerator(bridgeGame.getPlayerMovingHistory(), hasFailed);
        movingMap = new MovingMap(generator);
        outputView.printMap(movingMap);
        return hasFailed;
    }

    private void showResult() {
        System.out.println("최종 게임 결과");
        outputView.printMap(movingMap);
        outputView.printResult(bridgeGame.hasPlayerFailed(), bridgeGame.getTrialCount());
    }

    // TODO Try/catch 중복 코드 제거 검토
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

    private boolean askToRetry() {
        try {
            return inputView.readGameCommand();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return askToRetry();
        }
    }
}
