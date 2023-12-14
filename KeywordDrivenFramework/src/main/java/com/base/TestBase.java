package com.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
    public static WebDriver driver;
    public static Properties config;
    public static WebDriverWait wait;
    public static void initialize() {
        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream(Constants.WORKING_DIR+"\\src\\main\\resources\\config.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\1000066986\\eclipse-workspace\\KeywordDrivenFramework\\Drivers\\chromedriver.exe");
        // driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void navigate(String url) {
        driver.get(url);
    }

    public static void inputText(String locator, String locatorType, String value) {
        By by = getLocator(locator, locatorType);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(by).sendKeys(value);
        System.out.println("Successfully entered the value in the Textbox");
    }

    public static void clickElement(String locator, String locatorType) {
        By by = getLocator(locator, locatorType);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(by).click();
        System.out.println("Successfully Clicked the button in the Webpage");
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private static By getLocator(String locator, String locatorType) {
        if (locatorType == null || locatorType.isEmpty()) {
            // If LocatorType is not specified, assume it's an ID
            return By.id(locator);
        }

        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locator);
            case "name":
                return By.name(locator);
            case "xpath":
                return By.xpath(locator);
            case "css":
                return By.cssSelector(locator);
            case "tagname":
                return By.tagName(locator);
            case "linktext":
                return By.linkText(locator);
            // Add more locator types as needed
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
    }
}
