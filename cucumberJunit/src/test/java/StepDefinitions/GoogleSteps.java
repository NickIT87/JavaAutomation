package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class GoogleSteps {
    @Given("google page")
    public void go_to_google_page() {
        System.out.println("first");
    }

    @And("check search field appears")
    public void check_title() {
        System.out.println("second");
    }
}
