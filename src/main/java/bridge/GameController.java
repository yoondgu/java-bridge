package bridge;

import bridge.model.BridgeGame;
import bridge.view.InputView;
import bridge.view.MovingMap;
import bridge.view.OutputView;
import bridge.view.constants.OutputMessage;
import bridge.view.utils.MovingMapGenerator;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private BridgeGame bridgeGame;
    private MovingMap movingMap;

    public void run() {
        try {
            outputView.printMessage(OutputMessage.START_GAME);
            initializeBridgeGame();
            playRoundsUntilFailOrDone();
            showResult();
        } catch (Exception exception) {
            exception.printStackTrace();
            outputView.printErrorMessage(exception.getMessage());
        }
    }

    private void initializeBridgeGame() {
        outputView.printMessage(OutputMessage.ASK_BRIDGE_SIZE);
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

    private boolean playOneRound() {
        outputView.printMessage(OutputMessage.ASK_MOVING);
        bridgeGame.move(askMoving());
        boolean hasFailed = bridgeGame.hasPlayerFailed();
        // TODO generator 없이 MovingMap 객체 생성 검토
        MovingMapGenerator generator = new MovingMapGenerator(bridgeGame.getPlayerMovingHistory(), hasFailed);
        movingMap = new MovingMap(generator);
        outputView.printMap(movingMap);
        return hasFailed;
    }

    private void retryOrQuit() {
        outputView.printMessage(OutputMessage.ASK_RETRY_OR_QUIT);
        if (askToRetry()) {
            bridgeGame.retry();
            playRoundsUntilFailOrDone();
        }
    }

    private void showResult() {
        outputView.printMessage(OutputMessage.SHOW_RESULT);
        outputView.printMap(movingMap);
        outputView.printResult(bridgeGame.hasPlayerFailed(), bridgeGame.getTrialCount());
    }

    // TODO Try/catch 중복 코드 제거 검토
    private int askBridgeSize() {
        try {
            return inputView.readBridgeSize();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return askBridgeSize();
        }
    }

    private String askMoving() {
        try {
            return inputView.readMoving();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return askMoving();
        }
    }

    private boolean askToRetry() {
        try {
            return inputView.readGameCommand();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return askToRetry();
        }
    }
}
