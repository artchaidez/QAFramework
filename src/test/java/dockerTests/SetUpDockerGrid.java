package dockerTests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class SetUpDockerGrid {

    @BeforeTest
    void StartDockerGrid() throws IOException, InterruptedException {
        // cmd /c opens command prompt
        Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
        Thread.sleep(15000);
    }

    @AfterTest
    void StopDockerGrid() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
        Thread.sleep(5000);

        Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); // closes command prompt
    }

}
