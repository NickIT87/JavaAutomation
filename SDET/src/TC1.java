import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC1 {

    @BeforeMethod
    void beforeMethod() {
        System.out.println("This wil be execute before every method");
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("This wil be execute after every method");
    }

    @Test
    void test1() {
        System.out.println("This is test 1...");
    }

    @Test
    void test2() {
        System.out.println("This is test 2 ... ");
    }

}
