package bridge.view.utils;

import bridge.view.constants.Keyword;
import bridge.view.validator.ArgumentValidator;
import camp.nextstep.edu.missionutils.Console;

public class ConsoleReader {

    public int readLineAsIntegerInRange(int minimum, int maximum) {
        int readValue = readLineAsInteger();
        ArgumentValidator.validateIntegerInRange(readValue, minimum, maximum);
        return readValue;
    }

    public <T> T readLineAsKeyword(Keyword[] keywords) {
        String line = readLine();
        Keyword matchedKeyword = ArgumentValidator.validateMatchedKeyword(line, keywords);
        return matchedKeyword.getValue();
    }

    private int readLineAsInteger() {
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
