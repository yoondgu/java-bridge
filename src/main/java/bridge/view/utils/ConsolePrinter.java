package bridge.view.utils;

import java.util.List;

public class ConsolePrinter {

    public static void printMapDisplay(List<String> map) {
        map.forEach(System.out::println);
    }
}
