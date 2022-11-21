package bridge.view.utils;

import bridge.view.constants.OutputFormat;
import bridge.view.validator.ArgumentValidator;

public class ConsolePrinter {

    public void printLine(Object content) {
        ArgumentValidator.validateContentNotNull(content);
        System.out.println(content);
    }

    public void printFormattedLine(OutputFormat format, Object content) {
        ArgumentValidator.validateContentNotNull(content);
        ArgumentValidator.validateFormatValueNotNull(format);
        System.out.printf(format.getValue() + "\n", content);
    }
}
