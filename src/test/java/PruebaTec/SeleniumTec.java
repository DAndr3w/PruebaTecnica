package PruebaTec;

import org.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SeleniumTec extends Selenium {

    @BeforeClass
    public void setUp() {
        super.setUp(); // Llama al método setUp de la clase base
    }

    @Test
    public void navigateAndCaptureScreenshots() throws IOException {
        driver.get("https://www.selenium.dev/documentation/");
        String[] buttons = {
            "Documentation", "Overview", "WebDriver", "Selenium Manager", 
            "Grid", "IE Driver Server", "IDE", "Test Practices", "Legacy", "About"
        };

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (String button : buttons) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(button)));
            highlightElement(element);
            element.click();
            takeScreenshot(button);
            driver.navigate().back();
        }
    }

    private void highlightElement(WebElement element) {
        String originalStyle = element.getAttribute("style");
        executeJavaScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red; border-style: dashed;");
        // Restore original style after some time
        executeJavaScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }

    private void takeScreenshot(String buttonName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File outputDir = new File("screenshots");
        FileUtils.copyFile(screenshot, new File(outputDir, buttonName + "_screenshot.png"));

    }

    @AfterClass
    public void tearDown() {
        super.tearDown(); // Llama al método tearDown de la clase base
    }
}
