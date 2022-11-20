package bridge.view.utils;

import bridge.view.constants.CommandKeyword;
import bridge.view.constants.MovingKeyword;
import camp.nextstep.edu.missionutils.Console;

public class ConsoleReader {

    private ConsoleReader() { }

    public static boolean readLineAsKeyword(CommandKeyword[] keywords) {
        String line = readLine();
        for (CommandKeyword keyword : keywords) {
            if (keyword.getKey().equals(line)) {
                return keyword.getValue();
            }
        }
        throw new IllegalArgumentException("입력 오류: 해당 입력값은 지정된 키워드만 허용됩니다.");
    }

    public static String readLineAsKeyword(MovingKeyword[] keywords) {
        String line = readLine();
        for (MovingKeyword keyword : keywords) {
            if (keyword.getKey().equals(line)) {
                return keyword.getValue();
            }
        }
        throw new IllegalArgumentException("입력 오류: 해당 입력값은 지정된 키워드만 허용됩니다.");
    }

    public static int readLineAsInteger() {
        String line = readLine();
        // TODO 정규표현식 활용해서 검증로직 분리 검토(정규표현식 사용 시 문자열 길이 1인 경우 별도 검증 필요)
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("입력 오류: 해당 입력값은 정수값만 허용됩니다.");
        }
    }

    public static String readLine() {
        return Console.readLine();
    }
}
