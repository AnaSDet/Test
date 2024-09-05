package com.domo.automation.ui.steps.hooks;

import com.domo.automation.ui.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    //run before each scenario
    @Before
    public void beforeEachScenario() {
        WebDriverManager.chromedriver().setup();
    }

    @After
    public void afterEachScenario(Scenario scenario) {
        if (Driver.isDriverInitialized()) {
            Driver.takeScreenShot(scenario);
            Driver.closeDriver();
        }
    }
}
