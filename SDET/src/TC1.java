import org.testng.annotations.*;

public class TC1 {

    @BeforeClass
    void beforeClass() {
        System.out.println(" BEFORE CLASS !!! ");
    }

    @AfterClass
    void afterClass() {
        System.out.println(" AFTER CLASS ");
    }

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
