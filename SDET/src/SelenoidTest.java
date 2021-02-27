import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URI;


public class SelenoidTest {

    RemoteWebDriver driver;

    @BeforeClass
    void setup() throws MalformedURLException {
        System.out.println("Before Class setup ...");
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("88.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        driver = new RemoteWebDriver(
                URI.create("http://192.168.122.13:4444/wd/hub").toURL(),
                capabilities
        );
    }

    @Test
    public void testGoogleSearch() {
        driver.get("http://www.google.com/");
        //Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertTrue(searchBox.isDisplayed(), "searchbox is displayed");
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        //Thread.sleep(5000);  // Let the user actually see something!
    }

    @AfterClass
    void tearDown() {
        System.out.println("After Class setup ...");
        //driver.quit();
    }
}
