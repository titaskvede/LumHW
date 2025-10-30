package Task1.utils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import common.Utils;
import io.qameta.allure.Allure;
import org.testng.Assert;


public class Task1Utils extends Utils {
    public static boolean checkType(String illegalElement){
        try {
            Allure.step("Check for illegal elements, input: " + illegalElement, () -> {
                refresh();
                Task1Utils.waitForPageLoad();
                $("#number").setValue(illegalElement); // $ ->  Find first element
                $("#getFactorial").click(); // This is quite easy compared to Selenium :)
                try {
                    $("#resultDiv").shouldNotBe(empty, Duration.ofSeconds(2)); // Protect against slow loading
                } catch (Throwable ignored) {                }
                Utils.checkForScreenshot("strInput", "String input");
                SelenideElement resultDiv = $("#resultDiv");
                boolean result = resultDiv.getCssValue("color").contains("255, 0, 0");
                Assert.assertTrue(result, "Text color should be red, but was: " + resultDiv.getCssValue("color"));
            });
        } catch (AssertionError e) {
            return false;
        }
        return true;
    }
}
