package bridge.view.template;

import static bridge.view.constants.MovingKeyword.DOWN;
import static bridge.view.constants.MovingKeyword.UP;
import static bridge.view.constants.MovingMapFormat.DELIMITER;
import static bridge.view.constants.MovingMapFormat.PREFIX;
import static bridge.view.constants.MovingMapFormat.SUFFIX;

import bridge.view.constants.MovingMapDisplay;

import java.util.StringJoiner;

/**
 * 이동 현황 정보를 출력을 위해 정해진 지도 형식으로 저장하는 템플릿 클래스
 */
public class MovingMap {

    private final StringJoiner up = new StringJoiner(DELIMITER.getValue(), PREFIX.getValue(), SUFFIX.getValue());
    private final StringJoiner down = new StringJoiner(DELIMITER.getValue(), PREFIX.getValue(), SUFFIX.getValue());

    public void addOneRound(boolean hasFailed, String moving) {
        String upDisplay = MovingMapDisplay.convert(hasFailed, UP.isSameStepMoving(moving));
        String downDisplay = MovingMapDisplay.convert(hasFailed, DOWN.isSameStepMoving(moving));
        up.add(upDisplay);
        down.add(downDisplay);
    }

    @Override
    public String toString() {
        return up + "\n" + down;
    }
}
