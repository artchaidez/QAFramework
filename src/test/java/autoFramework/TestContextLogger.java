package autoFramework;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestContextLogger  {

    LocalDateTime myDateObj = LocalDateTime.now();

    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    /** Formats and logs debug message. */
    public void Debug(String message)
    {
        System.out.println(FormatTimeStampMessage("DEBUG", message));
    }

    /** Formats and logs info message.*/
    public void Info(String message)
    {
        System.out.println(FormatTimeStampMessage("INFO", "   " + message));
    }

    /** Formats and logs error message.*/
    public void Error(String message)
    {
        System.out.println(FormatTimeStampMessage("ERROR", "  " +message));
    }

    /** Formats and logs warning message.*/
    public void Warning(String message)
    {
        System.out.println(FormatTimeStampMessage("WARN", message));
    }

    /** Formats and logs pass message.*/
    public void Pass(String message)
    {
        System.out.println(FormatTimeStampMessage("PASS", message));
    }

    /** Formats and logs fail message.*/
    public void Fail(String message)
    {
        System.out.println(FormatTimeStampMessage("FAIL", message));
    }

    /** Formats and logs step message.*/
    public void Step(String message, int stepNumber)
    {
        String stars = "*****************************************************";
        message = "Step " + stepNumber + " - " + message;

        System.out.println(FormatTimeStampMessage("INFO", ""));
        System.out.println(FormatTimeStampMessage("STEP", stars));
        System.out.println(FormatTimeStampMessage("STEP", message));
        System.out.println(FormatTimeStampMessage("STEP", stars));
    }

    /** Formats message to be used by functions within TestContextLogger.*/
    public String FormatTimeStampMessage(String messageType, String message)
    {
        return MessageFormat.format("[{0}] [{1}] {2}", myDateObj.format(myFormatObj), messageType, message);
    }
}
