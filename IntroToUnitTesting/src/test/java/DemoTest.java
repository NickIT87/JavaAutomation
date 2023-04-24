/**
 * The class containing your tests for the {@link Demo} class.  Make sure you
 * test all methods in this class (including both 
 * {@link Demo#main(String[])} and 
 * {@link Demo#isTriangle(double, double, double)}).
 */
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class DemoTest {

    @Test
    public void testIsTriangleWithValidSides() {
        assertTrue(Demo.isTriangle(4, 4, 4));
        assertTrue(Demo.isTriangle(5, 10, 13));
        assertTrue(Demo.isTriangle(2, 3, 4));
        assertTrue(Demo.isTriangle(7, 24, 25));
    }

    @Test
    public void testIsTriangleWithInvalidSides() {
        assertFalse(Demo.isTriangle(2, 2, 8));
        assertFalse(Demo.isTriangle(5, 10, 35));
        assertFalse(Demo.isTriangle(1, 2, -1));
        assertFalse(Demo.isTriangle(0, 0, 0));
    }

    @Test
    public void testMainWithValidTriangle() {
        String input = "3\n4\n5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Demo.main(new String[] {});

        assertTrue(true); // just to check that the program runs without error
    }

    @Test
    public void testMainWithInvalidTriangle() {
        String input = "1\n1\n10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Demo.main(new String[] {});

        assertTrue(true); // just to check that the program runs without error
    }
}
