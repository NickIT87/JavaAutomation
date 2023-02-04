package StepDefinitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
//import org.junit.runner.RunWith;
import io.cucumber.testng.CucumberOptions;



//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"StepDefinitions"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
