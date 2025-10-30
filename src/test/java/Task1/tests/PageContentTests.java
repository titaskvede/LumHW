package Task1.tests;

import Task1.suite.Task1BaseTest;
import Task1.utils.Task1Utils;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.codeborne.selenide.*;
import io.qameta.allure.*;
import common.Utils;
import java.time.Year;
import static com.codeborne.selenide.Selenide.*;


public class PageContentTests extends Task1BaseTest {
    @Test(description = "Check page title")
    @Description("Check page title and expect it to be 'factorial'")
    @Severity(SeverityLevel.MINOR)
    @Epic("Exploratory testing")
    public void checkTitle() {
        Allure.step("Check page title", () -> {
            String title = title();
            Assert.assertNotNull(title);
            Allure.addAttachment("Page Title", title);
            Assert.assertEquals(title, "Factorial", "Page title should be 'Factorial'");
        });
    }

    @Test(description = "Check the visual of the page")
    @Description("Check if the visual of the page has changed.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Exploratory testing")
    public void exploratorySession(){
        Allure.step("Capture full HTML for analysis", () -> {
                    String html = WebDriverRunner.source();
                    Allure.addAttachment("Page HTML", "text/html", html, ".html");
        });
        Allure.step("Take a screenshot for visual record", () -> {
            // TODO create a screenshot comparison function
            Utils.checkForScreenshot("homepage", "Homepage Screenshot");
        });
    }

    @Test(description = "Check the copyright year")
    @Description("Check if the copyright extends to current year")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Exploratory testing")
    public void checkCorrectYear(){
        String text = $$("div.row-fluid p.wor_copyright").get(1).getText(); // $$ Finds all matches
        Allure.step("Read copyright text", () -> {
            Allure.addAttachment("Copyright Text", text);
        });
        Allure.step("Verify that text contains current year");
            String currentYear = String.valueOf(Year.now().getValue());
            Assert.assertTrue(text.contains(currentYear),"Expected text to contain year " + currentYear);
    }

    @Test(description = "Check Invalid entry types")
    @Description("Check how the website deals with illegal data types. Expect invalid types to be ignored")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Exploratory testing")
    public void checkInvalidTypes(){
        String[] illegalInputs = {
                "-1", "0.2", "!", "", " ", "a", "one",
                "1a", "a1", "55555", "\\t5", "NaN"
        };

        boolean finalResult = true;

        for (String input : illegalInputs) {
            if (!Task1Utils.checkType(input)) {
                finalResult =  false;
            }
        }
        Assert.assertTrue(finalResult, "Not all illegal types were found");
    }

    @Test(description = "Check if outgoing links references work", enabled = false)
    @Description("Press every out going link and check if the page successfully navigates to the screens")
    @Severity(SeverityLevel.MINOR)
    @Epic("Exploratory testing")
    public void checkPageHREFs(){
        // TODO
    }

    @Test(description = "Check if all answers are correect", enabled = false)
    @Description("Iterate through all available answers and check their resuls")
    @Severity(SeverityLevel.MINOR)
    @Epic("Exploratory testing")
    public void checkCorrectResults(){
        // TODO
    }
};
