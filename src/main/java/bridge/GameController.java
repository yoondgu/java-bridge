package bridge;

import bridge.model.BridgeGame;
import bridge.view.InputView;
import bridge.view.MovingMap;
import bridge.view.OutputView;
import bridge.view.constants.OutputMessage;
import bridge.view.utils.MovingMapGenerator;

import java.util.concurrent.Callable;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private BridgeGame bridgeGame;
    private MovingMap movingMap;

    public void run() {
        try {
            outputView.printMessage(OutputMessage.START_GAME);
            initializeBridgeGame(new BridgeRandomNumberGenerator());
            playRoundsUntilFailOrDone();
            showResult();
        } catch (Exception exception) {
            // TODO 기능 오류 메시지는 모두 여기서 출력한다면 여기에 "기능 오류:" 쓰기
            outputView.printErrorMessage(exception.getMessage());
        }
    }

    private void initializeBridgeGame(BridgeNumberGenerator generator) throws Exception {
        outputView.printMessage(OutputMessage.ASK_BRIDGE_SIZE);
        int bridgeSize = askUntilGetLegalAnswer(inputView::readBridgeSize);
        bridgeGame = new BridgeGame(bridgeSize, new BridgeMaker(generator));
    }

    private void playRoundsUntilFailOrDone() throws Exception {
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

    private boolean playOneRound() throws Exception {
        outputView.printMessage(OutputMessage.ASK_MOVING);
        String moving = askUntilGetLegalAnswer(inputView::readMoving);
        bridgeGame.move(moving);
        boolean hasFailed = bridgeGame.hasPlayerFailed();
        // TODO generator 없이 MovingMap 객체 생성 검토
        MovingMapGenerator generator = new MovingMapGenerator(bridgeGame.getPlayerMovingHistory(), hasFailed);
        movingMap = new MovingMap(generator);
        outputView.printMap(movingMap);
        return hasFailed;
    }

    private void retryOrQuit() throws Exception {
        outputView.printMessage(OutputMessage.ASK_RETRY_OR_QUIT);
        boolean toRetry = askUntilGetLegalAnswer(inputView::readGameCommand);
        if (toRetry) {
            bridgeGame.retry();
            playRoundsUntilFailOrDone();
        }
    }

    private void showResult() {
        outputView.printMessage(OutputMessage.SHOW_RESULT);
        outputView.printMap(movingMap);
        outputView.printResult(bridgeGame.hasPlayerFailed(), bridgeGame.getTrialCount());
    }

    private <T> T askUntilGetLegalAnswer(Callable<T> readInput) throws Exception {
        try {
            return readInput.call();
        } catch (IllegalArgumentException exception) {
            // TODO 입력 오류 메시지는 모두 여기서 출력한다면 "입력 오류:" 여기에 쓰기
            outputView.printErrorMessage(exception.getMessage());
            return askUntilGetLegalAnswer(readInput);
        }
    }
}
