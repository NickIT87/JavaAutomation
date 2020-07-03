import org.testng.annotations.Test;

public class SecondTestCase {
    @Test(priority = 1)
    void setup() {
        System.out.println("Opening browser 2");
    }

    @Test(priority = 3)
    void searchCustomer() {
        System.out.println("This is search customer test 2 ");
    }

    @Test(priority = 2)
    void addCustomer() {
        System.out.println("This is add customer test 2 ");
    }

    @Test(priority = 4)
    void teardown() {
        System.out.println("Closing browser 2 ");
    }
}
