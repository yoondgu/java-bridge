package bridge.view.template;

import bridge.view.utils.MovingMapGenerator;

import java.util.List;

public class MovingMap {

    private final List<String> map;

    public MovingMap(MovingMapGenerator generator) {
        this.map = generator.generateMap();
    }

    @Override
    public String toString() {
        return map.get(0) + " \n" + map.get(1);
    }
}
