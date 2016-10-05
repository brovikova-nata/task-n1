
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by milovanova-np on 05.10.2016.
 */
public class Run_test extends autotest_login_emailbox {


    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterTest
    public void quit() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    public void testAutotest() throws Exception {
        seturl();
        filldata();
        check_autorized();
    }

}
