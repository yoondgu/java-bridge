package bridge.view.utils;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleReader {

    private static final String REGEX_INTEGER = "^[^0]\\d*";

    public static int readLineAsInteger() {
        String line = readLine();
        if (line.matches(REGEX_INTEGER)) {
            throw new IllegalArgumentException("입력 오류: 해당 입력값은 정수만 허용됩니다.");
        }
        return Integer.parseInt(line);
    }

    public static String readLine() {
        return Console.readLine();
    }
}
