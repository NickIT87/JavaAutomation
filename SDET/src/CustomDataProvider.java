import org.testng.annotations.DataProvider;

public class CustomDataProvider {

    @DataProvider(name = "TestDataProvider")
    public Object[][] getData() {
        return new Object[][]{
                {"ChromeDriver"},{"testNG"}
        };
    }

}
