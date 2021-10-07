import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestMathExpressionTokenizer {


    @Test
    public void testGetTokens() throws Exception {
        ArrayList<String> res = MathExpressionTokenizer.getTokens("2 + 5 * (120 * 39) -2");
        ArrayList<String> answer = new ArrayList<String>(Arrays.asList("2","+", "5","*","(","120","*","39",")","-","2"));
        Assert.assertEquals(res,answer);
    }

}
