package StepDefinitions;

import PageObjects.SearchGooglePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


public class GoogleSteps {

    private WebDriver driver = null;

    @Given("google page")
    public void go_to_google_page() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        //chromeOptions.addArguments("--headless");                         // Option for headless browser
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        // Create the driver object
        this.driver = new ChromeDriver(chromeOptions);
        driver.get("https://google.com");
        System.out.println("first");
    }

    @And("check search field appears")
    public void check_title() {
        System.out.println("second");
    }

}
