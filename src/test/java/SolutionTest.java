import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void test_1() {
        String msg = "C";
        String encoded = Solution.chuckNorrisEncode(msg, true);
        Assert.assertEquals("0 0 00 0000 0 00", encoded);
    }

    @Test
    public void test_2() {
        String msg = "CC";
        String encoded = Solution.chuckNorrisEncode(msg, true);
        Assert.assertEquals("0 0 00 0000 0 000 00 0000 0 00", encoded);
    }

    @Test
    public void test_3() {
        String msg = "%";
        String encoded = Solution.chuckNorrisEncode(msg, true);
        Assert.assertEquals("00 0 0 0 00 00 0 0 00 0 0 0", encoded);
    }

    @Test
    public void test_4() {
        String msg = "Chuck Norris' keyboard has 2 keys: 0 and white space.";
        String encoded = Solution.chuckNorrisEncode(msg, true);
        Assert.assertEquals("0 0 00 0000 0 0000 00 0 0 0 00 000 0 000 00 0 0 0 00 0 0 000 00 000 0 0000 00 0 0 0 00 0 0 00 00 0 0 0 00 00000 0 0 00 00 0 000 00 0 0 00 00 0 0 0000000 00 00 0 0 00 0 0 000 00 00 0 0 00 0 0 00 00 0 0 0 00 00 0 0000 00 00 0 00 00 0 0 0 00 00 0 000 00 0 0 0 00 00000 0 00 00 0 0 0 00 0 0 0000 00 00 0 0 00 0 0 00000 00 00 0 000 00 000 0 0 00 0 0 00 00 0 0 000000 00 0000 0 0000 00 00 0 0 00 0 0 00 00 00 0 0 00 000 0 0 00 00000 0 00 00 0 0 0 00 000 0 00 00 0000 0 0000 00 00 0 00 00 0 0 0 00 000000 0 00 00 00 0 0 00 00 0 0 00 00000 0 00 00 0 0 0 00 0 0 0000 00 00 0 0 00 0 0 00000 00 00 0 0000 00 00 0 00 00 0 0 000 00 0 0 0 00 00 0 0 00 000000 0 00 00 00000 0 0 00 00000 0 00 00 0000 0 000 00 0 0 000 00 0 0 00 00 00 0 0 00 000 0 0 00 00000 0 000 00 0 0 00000 00 0 0 0 00 000 0 00 00 0 0 0 00 00 0 0000 00 0 0 0 00 00 0 00 00 00 0 0 00 0 0 0 00 0 0 0 00 00000 0 000 00 00 0 00000 00 0000 0 00 00 0000 0 000 00 000 0 0000 00 00 0 0 00 0 0 0 00 0 0 0 00 0 0 000 00 0", encoded);
    }
}