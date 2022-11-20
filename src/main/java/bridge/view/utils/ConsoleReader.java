package bridge.view.utils;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleReader {

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
