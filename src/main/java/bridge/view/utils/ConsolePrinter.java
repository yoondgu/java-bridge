package bridge.view.utils;

import bridge.view.constants.OutputFormat;

public class ConsolePrinter {

    public static void printLine(Object content) {
        validateContent(content);
        System.out.println(content);
    }

    public static void printFormattedLine(OutputFormat format, Object content) {
        validateContent(content);
        validateFormat(format);
        System.out.printf(format.getValue(), content);
    }

    private static void validateContent(Object content) {
        if (content == null) {
            throw new NullPointerException("기능 오류: 출력 메세지의 값이 null입니다.");
        }
    }

    private static void validateFormat(OutputFormat format) {
        validateContent(format);
        if (format.getValue() == null) {
            throw new NullPointerException("기능 오류: 출력 형식의 값이 null입니다.");
        }
    }
}
