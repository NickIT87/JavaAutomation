package StepDefinitions;

import PageObjects.SearchGooglePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


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
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.get("https://google.com");
    }

    @And("check search field appears")
    public void check_title() {
        SearchGooglePage gpage = new SearchGooglePage(driver);
        gpage.search_request("test");

        WebElement searchField = driver.findElement(By.name("q"));
        Assert.assertNotNull(searchField);
        driver.quit();
    }

}
