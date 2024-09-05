package com.domo.automation.ui.runners;
import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource(value = "ui/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.domo.automation.ui.steps")
@IncludeTags("Test")

public class UiRegressionRunner {
}
