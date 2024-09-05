package com.domo.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {


    //initialize WebDriver
    private WebDriver driver;

    //Constructor to initialize the WebDriver and PageFactory
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
