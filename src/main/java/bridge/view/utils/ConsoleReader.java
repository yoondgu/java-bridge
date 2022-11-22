package bridge.view.utils;

import bridge.view.constants.Keyword;
import bridge.view.validator.ArgumentValidator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 콘솔창의 입력값을 데이터 타입 및 형식에 맞게 읽는 클래스
 */
public class ConsoleReader {

    public int readLineAsIntegerInRange(int minimum, int maximum) throws IllegalArgumentException {
        int readValue = readLineAsInteger();
        ArgumentValidator.validateIntegerInRange(readValue, minimum, maximum);
        return readValue;
    }

    public <T> T readLineAsKeyword(Keyword[] keywords) throws IllegalArgumentException {
        String line = readLine();
        Keyword matchedKeyword = ArgumentValidator.validateMatchedKeyword(line, keywords);
        return matchedKeyword.getValue();
    }

    private int readLineAsInteger() throws IllegalArgumentException {
        String line = readLine();
        if (line.length() == 1) {
            ArgumentValidator.validateDigit(line.charAt(0));
        }
        ArgumentValidator.validateInteger(line);
        return Integer.parseInt(line);
    }

    private String readLine() {
        return Console.readLine();
    }
}
