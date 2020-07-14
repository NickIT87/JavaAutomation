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


public class ParametersExample {

    RemoteWebDriver driver;

    @BeforeClass
    @Parameters({"url"})
    void setup( String url ) throws MalformedURLException {
        System.out.println("Before Class setup ...");
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("77.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        driver = new RemoteWebDriver(
                URI.create("http://192.168.0.105:4444/wd/hub").toURL(),
                capabilities
        );

        System.out.println(url);
        driver.get("http://www.google.com");
    }

    @Test // null test
    public void testGoogleSearch() {
        //Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertTrue(searchBox.isDisplayed(), "searchbox is displayed");
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        //Thread.sleep(5000);  // Let the user actually see something!
    }

    @Test(priority = 1)
    public void testLogoLink() {
        WebElement logo = driver.findElementById("logo");
        logo.click();
    }

    @AfterClass
    void tearDown() {
        System.out.println("After Class setup ...");
        //driver.close();   // close browser
        driver.quit();    // close session
    }
}
