import org.testng.annotations.*;

public class TC2 {

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
    void test3() {
        System.out.println("This is test 3...");
    }

    @Test
    void test4() {
        System.out.println("This is test 4 ... ");
    }

    @BeforeTest
    void beforeTest() {
        System.out.println("BeFoReTeSt 3 4 ");
    }

    @AfterTest
    void afterTest() {
        System.out.println("AfTeRtEsT 3 4 ");
    }

    @BeforeSuite
    void beforeSuite() {
        System.out.println("+++ Before Suite +++");
    }

    @AfterSuite
    void afterSuite() {
        System.out.println("+++ After Suite +++");
    }

}
