package common;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Utils {
    public static class SoftCheck {
        public static void Step(String stepName, Assert.ThrowingRunnable action) {
            Allure.step(stepName, () -> {
                try {
                    action.run();
                } catch (Exception e) {
                    // Handle or ignore checked exceptions
                    System.out.println("Ignored exception: " + e.getMessage());
                    Allure.step(stepName + " (Exception ignored: " + e.getClass().getSimpleName() + ")", Status.FAILED);
                }
            });
        }
    }
    public static void checkForScreenshot(String fileName, String reportTitle) throws FileNotFoundException {
        // Function is used to create a screenshot and automatically add it into the report

        String path = Selenide.screenshot(fileName);
        assert path != null;
        File file = Paths.get(URI.create(path)).toFile(); // convert URI to File
        Allure.addAttachment(reportTitle, new FileInputStream(file));
    }

    public static void waitForPageLoad() {
        executeJavaScript("return document.readyState").equals("complete");
    }
}
