import org.junit.Assert;
import org.junit.Test;

public class TestRecursionParser {
    @Test
    public void testParse() throws Exception {
        RecursionParser parser = new RecursionParser("(2 + 3 * ( 4 - 5 ) + 6 - 7)^2");
        Assert.assertEquals(parser.parse(),new Fraction(4,1));
    }
}
