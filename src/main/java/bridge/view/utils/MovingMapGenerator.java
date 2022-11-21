package bridge.view.utils;

import bridge.view.constants.MovingKeyword;
import bridge.view.constants.MovingMapDisplay;
import bridge.view.constants.MovingMapFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

// TODO 생성 로직 단순화 검토
public class MovingMapGenerator {

    private final List<String> movingHistory;
    private final boolean isFailed;

    public MovingMapGenerator(List<String> movingHistory, boolean isFailed) {
        validateMovingHistory(movingHistory);
        this.movingHistory = movingHistory;
        this.isFailed = isFailed;
    }


    private void validateMovingHistory(List<String> movingHistory) {
        if (movingHistory == null) {
            throw new NullPointerException("출력 오류: 이동 결과가 null입니다.");
        }
        if (movingHistory.isEmpty()) {
            throw new IllegalArgumentException("출력 오류: 이동 결과에 저장된 정보가 없습니다.");
        }
    }

    public List<String> generateMap() {
        String mapDelimiter = MovingMapFormat.DELIMITER.getValue();
        StringJoiner up = new StringJoiner(mapDelimiter);
        StringJoiner down = new StringJoiner(mapDelimiter);
        addDisplayByMoving(up, down);
        return assembleDisplaysForMap(up, down);
    }

    private List<String> assembleDisplaysForMap(StringJoiner up, StringJoiner down) {
        String mapPrefix = MovingMapFormat.PREFIX.getValue();
        String mapSuffix = MovingMapFormat.SUFFIX.getValue();
        List<String> map = new ArrayList<>();
        map.add(mapPrefix + up + mapSuffix);
        map.add(mapPrefix + down + mapSuffix);
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
        return MovingMapDisplay.NOTHING.getDisplay();
    }

    private String convertResultToDisplay(boolean isFailedMoving) {
        if (isFailedMoving) {
            return MovingMapDisplay.FAIL.getDisplay();
        }
        return MovingMapDisplay.SUCCESS.getDisplay();
    }
}
