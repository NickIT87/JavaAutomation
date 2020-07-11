import org.testng.annotations.Test;

public class PriorityExample {

    @Test(priority = 0, enabled = false)
    void TestOne() {
        System.out.println("This is test 1");
    }

    @Test(priority = 1)
    void TestTwo() {
        System.out.println("This is test 2");
    }

    @Test(priority = 2)
    void TestThree() {
        System.out.println("This is test 3");
    }

    @Test(priority = 3)
    void TestFour() {
        System.out.println("This is test 4");
    }


}
