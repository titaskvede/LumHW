package Task1.suite;

import common.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import Task1.utils.Task1Utils;
import static com.codeborne.selenide.Selenide.open;

public class Task1BaseTest extends BaseTest {

    @BeforeClass()
    public void BeforeClass() {
        super.BeforeClass();
        open("https://qainterview.pythonanywhere.com");
        Task1Utils.waitForPageLoad();
    }

    @AfterTest()
    public void AfterTest() {
        super.AfterSuite();
    }
}