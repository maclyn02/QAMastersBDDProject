package BDD;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/Features/",
        tags ="@smoke",
        format = "html:target/CucumberHTMLReports")
public class RunCucumberTests {


}