package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class SearchGooglePage {

    WebDriver driver;

    public SearchGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.NAME, using = "q")
    private WebElement search_field;


    public void search_request(String searchRequest) {
        search_field.sendKeys(searchRequest);
        search_field.submit();
    }

}
