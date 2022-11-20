package bridge.view.utils;

import bridge.view.constants.MovingKeyword;
import bridge.view.constants.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MovingMapGenerator {

    private final List<String> movingHistory;
    private final boolean isFailed;

    public MovingMapGenerator(List<String> movingHistory, boolean isFailed) {
        this.movingHistory = movingHistory;
        this.isFailed = isFailed;
    }

    public List<String> generateMap() {
        StringJoiner up = new StringJoiner(" | ");
        StringJoiner down = new StringJoiner(" | ");
        addDisplayByMoving(up, down);
        return assembleDisplaysForMap(up, down);
    }

    private List<String> assembleDisplaysForMap(StringJoiner up, StringJoiner down) {
        List<String> map = new ArrayList<>();
        map.add("[ " + up + " ]");
        map.add("[ " + down + " ]");
        return map;
    }

    private void addDisplayByMoving(StringJoiner up, StringJoiner down) {
        for (int index = 0; index < movingHistory.size(); index++) {
            String moving = movingHistory.get(index);
            boolean isFailedMoving = isFailedMoving(index);
            up.add(convertMovingToDisplay(moving, MovingKeyword.UP, isFailedMoving));
            down.add(convertMovingToDisplay(moving, MovingKeyword.DOWN, isFailedMoving));
        }
    }

    private boolean isFailedMoving(int index) {
        if (index == movingHistory.size() - 1) {
            return isFailed;
        }
        return false;
    }

    private String convertMovingToDisplay(String moving, MovingKeyword movingKeyword, boolean isFailedMoving) {
        if (moving.equals(movingKeyword.getValue())) {
            return convertResultToDisplay(isFailedMoving);
        }
        return " ";
    }

    private String convertResultToDisplay(boolean isFailedMoving) {
        if (isFailedMoving) {
            return Result.FAIL.getKeyword();
        }
        return Result.SUCCESS.getKeyword();
    }
}
