package bridge.view.utils;

import bridge.view.constants.Keyword;
import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;

public class ConsoleReader {

    public int readLineAsIntegerInRange(int minimum, int maximum) {
        int readValue = readLineAsInteger();
        if (readValue < minimum || readValue > maximum) {
            throw new IllegalArgumentException("입력 오류: 해당 입력값은 지정된 범위 내의 정수값만 허용됩니다.");
        }
        return readValue;
    }

    public <T> T readLineAsKeyword(Keyword[] keywords) {
        String line = readLine();
        Keyword matchedKeyword = Arrays.stream(keywords)
                .filter(keyword -> keyword.getKey().equals(line))
                .findFirst()
                .orElseThrow(() -> {throw new IllegalArgumentException("입력 오류: 해당 입력값은 지정된 키워드만 허용됩니다.");});
        return matchedKeyword.getValue();
    }

    private int readLineAsInteger() {
        String line = readLine();
        // TODO 정규표현식 활용해서 검증로직 분리 검토(정규표현식 사용 시 문자열 길이 1인 경우 별도 검증 필요)
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("입력 오류: 해당 입력값은 정수값만 허용됩니다.");
        }
    }

    private String readLine() {
        return Console.readLine();
    }
}
