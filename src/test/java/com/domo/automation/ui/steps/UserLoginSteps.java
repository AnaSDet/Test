package com.domo.automation.ui.steps;

import com.domo.automation.ui.pages.LoginPage;
import com.domo.automation.ui.utils.ConfigReader;
import com.domo.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class UserLoginSteps {

    private final WebDriver driver;
    private final LoginPage loginPage;

    public UserLoginSteps(){
        this.driver = Driver.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    
    @Given("the user is on the login page {string}")
    public void the_user_is_on_the_login_page(String loginPageURL) {
      driver.get(ConfigReader.getPropertiesValue("loginPageURL"));

      assertTrue(loginPage.getMessage(), "Sign in page title mismatch");

    }

    @When("the user enters a valid company domain {string}")
    public void theUserEntersAValidCompanyDomain(String validDomainLogIn) {
      String domain = ConfigReader.getPropertiesValue("validDomain");
      loginPage.provideDomain(domain);

    }

    @When("the user clicks continue button and proceeds to the authentication page")
    public void theUserClicksContinueButtonAndProceedsToTheAuthenticationPage() {
      String authUrl = ConfigReader.getPropertiesValue("authURL");
      assertTrue(driver.getCurrentUrl().contains(authUrl), "Failed to navigate to the authentication page");

        driver.get("https://annasdet.domo.com/auth/index?redirectUrl=%2F");

    }

    @When("enters valid email {string} and password {string} and clicks the Sign In button")
    public void entersValidEmailAndPasswordAndClicksTheSignInButton(String emailTxt, String passwordTxt) {
      String email = ConfigReader.getPropertiesValue("validEmail");
      String password = ConfigReader.getPropertiesValue("validPassword");

      loginPage.loginCredentials(email, password);


    }

    @Then("the user should be logged in successfully and directed to the dashboard {string}")
    public void theUserShouldBeLoggedInSuccessfullyAndDirectedToTheDashboard(String expectedDashboardGettingStartedMessage) {

        //let's validate the URL
        String expectedURL = ConfigReader.getPropertiesValue("homePageUrlPart");

        //Let's wait for the URL to change
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try{
             wait.until(ExpectedConditions.urlContains(expectedURL));
        }  catch (TimeoutException e)    {
            fail("Failed on waiting for URL to change. Current URL: " + driver.getCurrentUrl());
        }

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(expectedURL), "Expected URL to be '" + expectedURL + "', but it was: " + currentUrl);


        //verify the dashboard by validating "Getting Started" text from the webpage
        try {

            assertTrue(loginPage.isGettingStartedElementDisplayed(), "Failed to find Getting Started element on the dashboard");
        }catch (TimeoutException e) {
            fail("Run out of time waiting for 'Getting Started' element. Page source: " + driver.getPageSource());
        }
    }
}
