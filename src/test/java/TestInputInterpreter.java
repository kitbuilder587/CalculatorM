import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class TestInputInterpreter {
    @Test
    public void testCalculateAll() throws Exception {
        InputInterpreter inputInterpreter = new InputInterpreter(new DefaultCalculator());
        String res = inputInterpreter.calculateAll("[2,1]+[3,1]-[5,1]",'+','-');
        Assert.assertEquals(res,"[0,1]");
        res = inputInterpreter.calculateAll("[2,1]*[3,1]/[5,1]",'*','/');
        Assert.assertEquals(res,"[6,5]");
    }

    @Test
    public void testGetTheBiggestNumberToTheLeft() throws Exception {
        InputInterpreter inputInterpreter = new InputInterpreter(new DefaultCalculator());
        Fraction res = inputInterpreter.getTheBiggestNumberToTheLeft("[2,1]+[3,1]-[5,1]",3);
        Assert.assertEquals(res,new Fraction(2,1));
    }
    @Test
    public void testGetTheBiggestNumberToTheRight() throws Exception {
        InputInterpreter inputInterpreter = new InputInterpreter(new DefaultCalculator());
        Fraction res = inputInterpreter.getTheBiggestNumberToTheRight("[2,1]+[3,1]-[5,1]",7);
        Assert.assertEquals(res,new Fraction(3,1));
    }

    @Test
    public void testCalculateResultWithoutBrackets() throws Exception {
        InputInterpreter inputInterpreter = new InputInterpreter(new DefaultCalculator());
        Fraction res = inputInterpreter.calculateResultWithoutBrackets(inputInterpreter.refactorStringWithoutMistakes("-2-3-5*2"));
        Assert.assertEquals(res,new Fraction(-15,1));
        res = inputInterpreter.calculateResultWithoutBrackets(inputInterpreter.refactorStringWithoutMistakes("-2*8-3+5*2^2-3"));
        Assert.assertEquals(res,new Fraction(-2,1));
        res = inputInterpreter.calculateResultWithoutBrackets(inputInterpreter.refactorStringWithoutMistakes("-2/8-3+5*2^2-3"));
        Assert.assertEquals(res,new Fraction(55,4));

    }

    @Test
    public void testCalculateResult() throws Exception {
        InputInterpreter inputInterpreter = new InputInterpreter(new DefaultCalculator());
        Fraction res = inputInterpreter.calculateResult("-(2-(-3)^2)-5*2");
        Assert.assertEquals(res,new Fraction(-3,1));
        res = inputInterpreter.calculateResult("-(2-(-3/10)^2)-5*2");
        Assert.assertEquals(res,new Fraction(-1191,100));
        res = inputInterpreter.calculateResult("10000^(100-1)");
        Assert.assertEquals(res,new Fraction(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),new BigInteger("1")));
    }
}
