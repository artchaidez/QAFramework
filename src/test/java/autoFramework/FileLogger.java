package autoFramework;

import org.apache.commons.lang3.NotImplementedException;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {
    private String logFile;
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
    public String LogFileName;

    //figure out how to combine paths
    public FileLogger(String dirPath, String fileName)
    {
        LogFileName = MessageFormat.format("Log_{0}.txt", fileName);
        logFile = dirPath + "/" + LogFileName;
        File directory = new File(logFile);

        if(!directory.exists())
        {
            directory.mkdir();
        }
    }

    private void Log(String status, String details)
    {
        details = MessageFormat.format("[{0}] [{1}] {2}", myDateObj.format(myFormatObj), status, details);

        //ToDo Need to learn about locks for threads and added here
    }

    public void Info(String details)
    {
        Log("INFO", details);
    }

    public void Debug(String details)
    {
        throw new NotImplementedException();
    }

    public void Warning(String details)
    {
        Log("WARN", details);
    }

    public void Fail(String details)
    {
        Log("FAIL", details);
    }

    public void Pass(String details)
    {
        Log("PASS", details);
    }

    public void Error(String details)
    {
        Log("ERROR", details);
    }

    public void Step(String message, int stepNumber)
    {
        String stars = "*************************************************";
        message = "Step" + stepNumber + " - " + message;

        Log("INFO", "");
        Log("STEP", stars);
        Log("STEP", message);
        Log("STEP", stars);
    }
}
