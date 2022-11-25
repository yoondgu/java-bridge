package bridge.controller.constants;

public enum GameStatus {

    PLAYING(),
    FAILED(),
    SUCCEED();

    public static GameStatus determineStatus(boolean hasGameFailed, boolean hasGameDone) {
        if (hasGameFailed) {
            return FAILED;
        }
        if (hasGameDone) {
            return SUCCEED;
        }
        return PLAYING;
    }
}
