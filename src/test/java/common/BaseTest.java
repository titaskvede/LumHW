package common;

import Task1.utils.Task1Utils;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.refresh;

public class BaseTest {
    @BeforeClass
    public void BeforeClass() {
        Configuration.browser = "chrome";
        Configuration.headless = false;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Temp/chrome-profile");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");

        Configuration.browserCapabilities = options;
        Configuration.driverManagerEnabled = true;
    }

    @AfterTest()
    public void AfterTest() {
        refresh();
        Task1Utils.waitForPageLoad();
    }

    @AfterSuite
    public void AfterSuite() {
        closeWebDriver();
    }
}