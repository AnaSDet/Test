package com.domo.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                String browser = ConfigReader.getPropertiesValue("browser");
                switch (browser.toLowerCase()) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "safari":
                        WebDriverManager.safaridriver().setup();
                        driver = new SafariDriver();
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        driver = new InternetExplorerDriver();
                        break;
                    case "headless":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions headlessOptions = new ChromeOptions();
                        headlessOptions.addArguments("--headless");
                        headlessOptions.addArguments("--window-size=1920,1080");
                        headlessOptions.addArguments("--disable-extensions");
                        headlessOptions.addArguments("--proxy-server='direct://'");
                        headlessOptions.addArguments("--proxy-bypass-list=*");
                        headlessOptions.addArguments("--start-maximized");
                        headlessOptions.addArguments("--remote-allow-origins=*");
                        driver = new ChromeDriver(headlessOptions);
                        break;
                    default:
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--remote-allow-origins=*");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        driver = new ChromeDriver(options);
                        break;
                }

                if (driver != null) {
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
                    driver.manage().window().maximize();
                }
            } catch (Exception e) {
                System.err.println("Error initializing WebDriver: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (driver == null) {
            System.err.println("WebDriver initialization failed.");
        }

        return driver;
    }

    public static void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            try {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (Exception e) {
                System.err.println("Failed to take screenshot: " + e.getMessage());
            }
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error closing WebDriver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    public static boolean isDriverInitialized() {
        return driver != null;
    }
}
