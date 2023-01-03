package autoFramework;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestContextLogger  {

    LocalDateTime myDateObj = LocalDateTime.now();

    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    public void Debug(String message)
    {
        System.out.println(FormatTimeStampMessage("DEBUG", message));
    }

    public void Info(String message)
    {
        System.out.println(FormatTimeStampMessage("INFO", "   " + message));
    }

    public void Error(String message)
    {
        System.out.println(FormatTimeStampMessage("ERROR", message));
    }

    public void Warning(String message)
    {
        System.out.println(FormatTimeStampMessage("WARN", message));
    }

    public void Pass(String message)
    {
        System.out.println(FormatTimeStampMessage("PASS", message));
    }

    public void Fail(String message)
    {
        System.out.println(FormatTimeStampMessage("FAIL", message));
    }

    public void Step(String message, int stepNumber)
    {
        String stars = "*****************************************************";
        message = "Step " + stepNumber + " - " + message;

        System.out.println(FormatTimeStampMessage("INFO", ""));
        System.out.println(FormatTimeStampMessage("STEP", stars));
        System.out.println(FormatTimeStampMessage("STEP", message));
        System.out.println(FormatTimeStampMessage("STEP", stars));
    }

    public String FormatTimeStampMessage(String messageType, String message)
    {
        return MessageFormat.format("[{0}] [{1}] {2}", myDateObj.format(myFormatObj), messageType, message);
    }
}
