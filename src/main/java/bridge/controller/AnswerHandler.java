package bridge.controller;

import bridge.view.OutputView;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class AnswerHandler {

    private final OutputView outputView;

    public AnswerHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    /**
     * 사용자에게 입력값을 받는다.
     * 잘못된 입력값으로 인한 예외 발생 시 다시 입력값을 받는다.
     */
    public <T> T askUntilGetLegalAnswer(Supplier<T> readInput) {
        while (true) {
            try {
                return readInput.get();
            } catch (IllegalArgumentException exception) {
                outputView.printUserInputErrorMessage(exception.getMessage());
            }
        }
    }

    /**
     * 사용자에게 정해진 범위 내의 입력값을 받는다.
     * 잘못된 입력값으로 인한 예외 발생 시 다시 입력값을 받는다.
     */
    public <R> R askUntilGetLegalAnswer(BiFunction<Integer, Integer, R> readInput,
                                         int minimum, int maximum) {
        while (true) {
            try {
                return readInput.apply(minimum, maximum);
            } catch (IllegalArgumentException exception) {
                outputView.printUserInputErrorMessage(exception.getMessage());
            }
        }
    }
}
