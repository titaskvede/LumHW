package Task2.tests;

import Task2.suite.Task2BaseTest;
import common.Utils;
import common.Utils.SoftCheck;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import com.codeborne.selenide.*;
import io.qameta.allure.Description;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class SmokeTest extends Task2BaseTest {
    private ElementsCollection options; // global variable to use in a pretty ay for steps
    private String chosenTitle;
    private String castMember;
    /**
     Testing steps:
        +*Open imdb.com
        +*Search for "QA" with the search bar
        +*When dropdown opens, save the name of the first title
        +*Click on the first title
        +*Verify that page title matches the one saved from the dropdown
        +*Verify there are more than 3 members in the "top cast section"
        +*Click on the 3rd profile in the "top cast section"
        +*Verify that correct profile have opened
     */
    @Test(description = "Smoke test")
    @Description("Test IMDb basic functionalities")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Smoke test")
    public void smokeTest() {
        SoftCheck.Step("Open IMDb homepage", () -> {
            open("https://www.imdb.com/");
            Utils.waitForPageLoad();
            Utils.checkForScreenshot("imdbMainPage", "Main page of IMDB");
        });

        SoftCheck.Step("Check if cookies appear", () -> {
            $("[data-testid='accept-button']").shouldBe(visible, Duration.ofSeconds(3)).click();
        });

        SoftCheck.Step("Search for 'QA' using search bar", () -> {
            $("[data-testid='suggestion-search']").shouldBe(visible, Duration.ofSeconds(5)).setValue("QA");
            $("#react-autowhatever-navSuggestionSearch")
                    .shouldBe(visible, Duration.ofSeconds(5));
            options = $("#react-autowhatever-navSuggestionSearch").$$(":scope li")
                    .shouldBe(sizeGreaterThan(0), Duration.ofSeconds(5)); // Sometimes the options themselves are not loaded fast enough
            Utils.checkForScreenshot("dropDownOptions", "Current drop down options");
        });

        SoftCheck.Step("Press the first title of series/movie", () -> {
            int option_number = 0;
            chosenTitle = options.get(option_number).$(".searchResult__constTitle").text();

            // TODO depending on seasonal events, this check could be increased. Investigate a way to detect if its a proper title
            if (chosenTitle.equals("Halloween Family Fun")) {
                // Halloween period brings up an article first. Ignore it to continue testing
                option_number = 1;
                chosenTitle = options.get(option_number).$(".searchResult__constTitle").text();
            }

            Allure.addAttachment("Selected IMDb title", chosenTitle);
            options.get(option_number).click();
        });

        SoftCheck.Step("Verify page title matches saved dropdown title", () -> {
            String pageTitle = getPageTitle();

            Allure.addAttachment("Page Title", pageTitle);
            // Compare with saved title
            Utils.checkForScreenshot("dropDownOptions", "Current drop down options");
            Assert.assertEquals(pageTitle, chosenTitle, "Page title should match the selected dropdown title");

        });

        SoftCheck.Step("Check the Top cast", () -> {
            $("section[data-testid='title-cast']").scrollTo();
            Thread.sleep(2000);
            Utils.checkForScreenshot("dropDownOptions", "Current drop down options");

            ElementsCollection castItems = $("[data-testid='title-cast']")
                    .$$("[data-testid='title-cast-item']");
            Assert.assertTrue(castItems.size() > 3, "Top cast item should have at least 3 items");
            castMember = castItems.get(2).
                    $("[data-testid='title-cast-item__actor").getText();
            Allure.addAttachment("Target testing cast", castMember);
            castItems.get(2).$("[data-testid='title-cast-item__actor").click();
            Utils.waitForPageLoad();
        });

        SoftCheck.Step("Check the Third cast member", () -> {
            Utils.checkForScreenshot("castMemberProfile", "Third cast member profile");
            Assert.assertEquals(castMember, getPageTitle(), "Check if correct profile has been opened");
        });
    }

    public String getPageTitle() {
        return $("h1[data-testid='hero__pageTitle'] .hero__primary-text")
                .shouldBe(visible, Duration.ofSeconds(5)) //This should force the page to wait
                .getText();
    }
}
