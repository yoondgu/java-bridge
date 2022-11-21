package bridge.view.validator;

import bridge.view.constants.Keyword;
import bridge.view.constants.OutputFormat;

import java.util.Arrays;

public class ArgumentValidator {

    private static final String REGEX_INTEGER = "^[^0]\\d*";

    public static void validateIntegerInRange(int value, int minimum, int maximum) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException("주어진 값이 지정된 범위 내의 수가 아닙니다.");
        }
    }

    public static Keyword validateMatchedKeyword(String value, Keyword[] keywords) {
        return Arrays.stream(keywords)
                .filter(keyword -> keyword.getKey().equals(value))
                .findFirst()
                .orElseThrow(() -> {throw new IllegalArgumentException("주어진 값이 지정된 키워드가 아닙니다.");});
    }

    public static void validateDigit(char value) {
        if (Character.isLetter(value)) {
            throw new IllegalArgumentException("주어진 값이 정수가 아닙니다.");
        }
    }

    public static void validateInteger(String value) {
        if (!value.matches(REGEX_INTEGER)) {
            throw new IllegalArgumentException("주어진 값이 정수가 아닙니다.");
        }
    }

    public static void validateContentNotNull(Object content) {
        if (content == null) {
            throw new NullPointerException("출력할 정보의 값이 null입니다.");
        }
    }

    public static void validateFormatValueNotNull(OutputFormat format) {
        validateContentNotNull(format);
        if (format.getValue() == null) {
            throw new NullPointerException("출력할 형식의 값이 null입니다.");
        }
    }
}
