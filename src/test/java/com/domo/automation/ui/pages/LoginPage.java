package com.domo.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{


    public LoginPage(WebDriver driver){
      super(driver); //call constructor from the parent class
   }


    @FindBy(xpath = "//input[@id='domain']")
    private WebElement domainInput;

    @FindBy(xpath = "//button[@name='submit']")
    private WebElement continueBtn;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement emailTxt;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTxt;

    @FindBy(xpath = "//div[@class='db-checkbox-widget']")
    private WebElement rememberMeBtn;

    @FindBy(xpath = "//div[@class='float-right ng-scope']")
    private WebElement forgotPasswordBox;

    @FindBy(xpath = "//button[@class='sign-in db-button']")
    private WebElement signInBtn;


    @FindBy(xpath = "//h1[contains(text(),'Sign in to Domo Support')]")
    private WebElement titleSignInDomoSupport;


    @FindBy(xpath = "//div[contains(text(),'Getting Started')]")
    private WebElement gettingStartedElement ;

    public void provideDomain(String domain){
        domainInput.sendKeys(domain);

        continueBtn.click();
    }

    public void loginCredentials(String userName, String password){
        emailTxt.sendKeys(userName);
        passwordTxt.sendKeys(password);

        signInBtn.click();
    }

    public boolean getMessage(){
       return titleSignInDomoSupport.isDisplayed();
    }
    public boolean isGettingStartedElementDisplayed(){
        return gettingStartedElement.isDisplayed();
    }
}
