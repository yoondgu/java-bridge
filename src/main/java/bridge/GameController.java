package bridge;

import bridge.model.BridgeGame;
import bridge.model.domains.constants.BridgeSize;
import bridge.view.InputView;
import bridge.view.OutputView;
import bridge.view.constants.OutputMessage;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private BridgeGame bridgeGame;

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

    private void initializeBridgeGame(BridgeNumberGenerator generator) {
        outputView.printMessage(OutputMessage.ASK_BRIDGE_SIZE);
        int bridgeSize = askUntilGetLegalAnswer(inputView::readBridgeSize,
                BridgeSize.MINIMUM.getValue(), BridgeSize.MAXIMUM.getValue());
        bridgeGame = new BridgeGame(bridgeSize, new BridgeMaker(generator));
    }

    private void playRoundsUntilFailOrDone() throws Exception {
        playOneRound();
        if (bridgeGame.hasPlayerFailed()) {
            retryOrQuit();
            return;
        }
        if (!bridgeGame.hasAllMovingDone()) {
            playRoundsUntilFailOrDone();
        }
    }

    private void playOneRound() {
        outputView.printMessage(OutputMessage.ASK_MOVING);
        String moving = askUntilGetLegalAnswer(inputView::readMoving);
        bridgeGame.move(moving);
        outputView.printMap(bridgeGame.hasPlayerFailed(), bridgeGame.getPlayerMovingHistory());
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
        boolean hasFailed = bridgeGame.hasPlayerFailed();
        outputView.printMap(hasFailed, bridgeGame.getPlayerMovingHistory());
        outputView.printResult(hasFailed, bridgeGame.getTrialCount());
    }

    private <T> T askUntilGetLegalAnswer(Supplier<T> readInput) {
        try {
            return readInput.get();
        } catch (IllegalArgumentException exception) {
            // TODO 입력 오류 메시지는 모두 여기서 출력한다면 "입력 오류:" 여기에 쓰기
            outputView.printErrorMessage(exception.getMessage());
            return askUntilGetLegalAnswer(readInput);
        }
    }
    private <R> R askUntilGetLegalAnswer(BiFunction<Integer, Integer, R> readInput,
                                         int minimum, int maximum) {
        try {
            return readInput.apply(minimum, maximum);
        } catch (IllegalArgumentException exception) {
            // TODO 입력 오류 메시지는 모두 여기서 출력한다면 "입력 오류:" 여기에 쓰기
            outputView.printErrorMessage(exception.getMessage());
            return askUntilGetLegalAnswer(readInput, minimum, maximum);
        }
    }
}
