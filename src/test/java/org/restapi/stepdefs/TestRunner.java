package org.restapi.stepdefs;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/cucumberscripts",   // Ensure feature files are here
        glue = "org.restapi.stepdefs",                       // Package where step definitions exist
        plugin = {"pretty", "json:target/cucumber-json-reports/cucumber.json", "html:target/cucumber-html-reports/cucumber.html"},
        monochrome = true
)
public class TestRunner {
}
