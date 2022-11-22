package bridge.view.template;

import bridge.view.constants.MovingKeyword;
import bridge.view.constants.MovingMapDisplay;
import bridge.view.constants.MovingMapFormat;

import java.util.StringJoiner;

public class MovingMap {

    private final StringJoiner up = new StringJoiner(MovingMapFormat.DELIMITER.getValue());
    private final StringJoiner down = new StringJoiner(MovingMapFormat.DELIMITER.getValue());

    public void addOneRound(boolean hasFailed, String moving) {
        up.add(MovingMapDisplay.convertMovingToDisplay(MovingKeyword.UP.isSameStepMoving(moving), hasFailed));
        down.add(MovingMapDisplay.convertMovingToDisplay(MovingKeyword.DOWN.isSameStepMoving(moving), hasFailed));
    }

    @Override
    public String toString() {
        return MovingMapFormat.PREFIX.getValue() + up + MovingMapFormat.SUFFIX.getValue() + "\n"
            + MovingMapFormat.PREFIX.getValue() + down + MovingMapFormat.SUFFIX.getValue();
    }
}
