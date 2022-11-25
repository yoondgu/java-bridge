package bridge.controller;

import static bridge.controller.constants.GameStatus.FAILED;
import static bridge.controller.constants.GameStatus.PLAYING;
import static bridge.model.domains.constants.BridgeSize.MAXIMUM;
import static bridge.model.domains.constants.BridgeSize.MINIMUM;
import static bridge.view.constants.OutputMessage.ASK_BRIDGE_SIZE;
import static bridge.view.constants.OutputMessage.ASK_MOVING;
import static bridge.view.constants.OutputMessage.ASK_RETRY_OR_QUIT;
import static bridge.view.constants.OutputMessage.SHOW_RESULT;
import static bridge.view.constants.OutputMessage.START_GAME;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.controller.constants.GameStatus;
import bridge.model.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

/**
 * 사용자 입출력 및 게임 결과의 상호작용을 흐름에 따라 실행하고 관리하는 클래스
 */
public class BridgeGameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final AnswerHandler answerHandler = new AnswerHandler(outputView);

    /**
     * 다리 게임을 실행한다.
     */
    public void run() {
        try {
            outputView.printMessage(START_GAME);
            BridgeGame bridgeGame = createNewGame(new BridgeRandomNumberGenerator());
            GameStatus finalStatus = playGameUntilEnds(bridgeGame);
            showResult(finalStatus, bridgeGame.getTrialCount());
        } catch (Exception exception) {
            outputView.printGameErrorMessage(exception.getMessage());
        }
    }

    /**
     * 정해진 다리 번호 생성기를 이용해 다리 게임의 기본 값을 설정한다.
     */
    private BridgeGame createNewGame(BridgeNumberGenerator generator) {
        outputView.printMessage(ASK_BRIDGE_SIZE);
        int bridgeSize = answerHandler.askUntilGetLegalAnswer(
                inputView::readBridgeSize,MINIMUM.getValue(), MAXIMUM.getValue()
        );
        return new BridgeGame(bridgeSize, new BridgeMaker(generator));
    }

    /**
     * 사용자가 다리 건너기를 실패하거나, 모두 완료할 때까지 게임 라운드를 반복한다.
     */
    private GameStatus playGameUntilEnds(BridgeGame bridgeGame) {
        GameStatus gameStatus;
        do {
            gameStatus = playOneRound(bridgeGame);
        } while (gameStatus == PLAYING);
        if (gameStatus == FAILED) {
            retryOrQuit(bridgeGame);
        }
        return gameStatus;
    }

    /**
     * 하나의 게임 라운드를 실행한다.
     */
    private GameStatus playOneRound(BridgeGame bridgeGame) {
        outputView.printMessage(ASK_MOVING);
        String moving = answerHandler.askUntilGetLegalAnswer(inputView::readMoving);
        bridgeGame.move(moving);
        outputView.updateMovingMap(bridgeGame.hasGameFailed(), moving);
        outputView.printMap();
        return GameStatus.determineStatus(bridgeGame.hasGameFailed(), bridgeGame.hasGameDone());
    }

    /**
     * 사용자의 선택에 따라 게임을 재시도하거나 종료한다.
     */
    private void retryOrQuit(BridgeGame bridgeGame) {
        outputView.printMessage(ASK_RETRY_OR_QUIT);
        boolean toRetry = answerHandler.askUntilGetLegalAnswer(inputView::readGameCommand);
        if (toRetry) {
            bridgeGame.retry();
            outputView.resetMovingMap();
            playGameUntilEnds(bridgeGame);
        }
    }

    /**
     * 최종 결과를 출력한다.
     */
    private void showResult(GameStatus gameStatus, int trialCount) {
        outputView.printMessage(SHOW_RESULT);
        outputView.printMap();
        outputView.printResult(gameStatus, trialCount);
    }
}
