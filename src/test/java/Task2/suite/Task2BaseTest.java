package Task2.suite;

import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Task2BaseTest extends BaseTest {

    @BeforeClass()
    public void BeforeClass() {
        super.BeforeClass();
        open("https://www.imdb.com");
        WebDriverRunner.getWebDriver().manage().deleteAllCookies(); // Reset Cookies before trying to test
        getWebDriver().manage().window().maximize();
    }

    @AfterTest()
    public void AfterTest() {
        super.AfterSuite();
    }
}
