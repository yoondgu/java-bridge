package bridge.view.constants;

import bridge.controller.constants.GameStatus;

/**
 * 최종 결과를 표현하는 키워드를 저장하고 값에 따라 변환해주는 상수 클래스
 */
public enum ResultKeyword implements Keyword {

    SUCCESS("성공", GameStatus.SUCCEED),
    FAIL("실패", GameStatus.FAILED);

    private final String key;
    private final GameStatus value;

    ResultKeyword(String key, GameStatus value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @SuppressWarnings("unchecked")
    @Override
    public GameStatus getValue() {
        return value;
    }

    public static String convertValueByGameStatus(GameStatus gameStatus) {
        if (gameStatus == GameStatus.SUCCEED) {
            return SUCCESS.getKey();
        }
        if (gameStatus == GameStatus.FAILED) {
            return FAIL.getKey();
        }
        return null;
    }
}
