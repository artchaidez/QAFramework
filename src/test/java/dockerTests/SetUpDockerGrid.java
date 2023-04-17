package dockerTests;

import org.testng.annotations.*;

import java.io.IOException;

public class SetUpDockerGrid {

    // TODO: Figure out how to make these commands on Jenkins
    // Runs on machine by default so this needs to be removed
/**
    @BeforeSuite
    void StartDockerGrid() throws IOException, InterruptedException {
        // cmd /c opens command prompt
        Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
        // Does not sleep on machine
        Thread.sleep(20000);
    }

    @AfterSuite
    void StopDockerGrid() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
        Thread.sleep(5000);

        Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); // closes command prompt
    }
*/

}
