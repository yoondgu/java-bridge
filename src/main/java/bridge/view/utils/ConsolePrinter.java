package bridge.view.utils;

import bridge.view.constants.OutputFormat;
import bridge.view.validator.ArgumentValidator;

/**
 * 콘솔 창에 주어진 정보를 출력하는 클래스
 */
public class ConsolePrinter {

    public void printLine(Object content) throws NullPointerException {
        ArgumentValidator.validateContentNotNull(content);
        System.out.println(content);
    }

    public void printFormattedLine(OutputFormat format, Object content) throws NullPointerException {
        ArgumentValidator.validateContentNotNull(content);
        ArgumentValidator.validateFormatValueNotNull(format);
        System.out.printf(format.getValue() + "\n", content);
    }
}
