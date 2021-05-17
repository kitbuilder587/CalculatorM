import org.junit.Assert;
import org.junit.Test;

public class TestFraction {
    @Test
    public void testAdd() throws Exception {
        Fraction f1 = new Fraction(2,3);
        Fraction f2 = new Fraction(3,7);
        Assert.assertEquals(f1.add(f2),new Fraction(23,21));

        f1 = new Fraction(5,10);
        f2 = new Fraction(30,70);
        Assert.assertEquals(f1.add(f2),new Fraction(13,14));
    }
    @Test
    public void testMultiply() throws Exception {
        Fraction f1 = new Fraction(2,3);
        Fraction f2 = new Fraction(3,7);
        Assert.assertEquals(f1.multiply(f2),new Fraction(2,7));

        f1 = new Fraction(5,10);
        f2 = new Fraction(30,70);
        Assert.assertEquals(f1.multiply(f2),new Fraction(3,14));
    }

    @Test
    public void testDivide() throws Exception {
        Fraction f1 = new Fraction(2,3);
        Fraction f2 = new Fraction(3,7);
        Assert.assertEquals(f1.divide(f2),new Fraction(14,9));

        f1 = new Fraction(5,10);
        f2 = new Fraction(30,70);
        Assert.assertEquals(f1.divide(f2),new Fraction(7,6));
    }
}
