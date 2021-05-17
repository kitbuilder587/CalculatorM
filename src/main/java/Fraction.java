import java.math.BigInteger;
import java.util.Objects;

public class Fraction {
    private BigInteger a;
    private BigInteger b;


    public Fraction(int a, int b) throws Exception {
        Fraction f = new Fraction(new BigInteger(String.valueOf(a)),new BigInteger(String.valueOf(b)));
        this.a = f.getA();
        this.b = f.getB();
    }

    public Fraction(BigInteger a, BigInteger b) throws Exception {
        if(b.equals(BigInteger.ZERO)){
            throw new Exception("Cannot make fraction");
        }
        BigInteger gcd = a.gcd(b);
        this.a = a.divide(gcd);
        this.b = b.divide(gcd);
    }

    public Fraction add(Fraction fraction) throws Exception {
        BigInteger a2 = fraction.getA();
        BigInteger b2 = fraction.getB();
        BigInteger lcm = b2.multiply(b).divide(b2.gcd(b));
        BigInteger newA = a.multiply(lcm.divide(b)).add(a2.multiply(lcm.divide(b2)));
        return new Fraction(newA,lcm);
    }

    public Fraction multiply(Fraction fraction) throws Exception {
        BigInteger newA = fraction.getA().multiply(a);
        BigInteger newB = fraction.getB().multiply(b);
        return new Fraction(newA,newB);
    }

    public Fraction reciprocal() throws Exception {
        if(a.equals(BigInteger.ZERO)){
            throw new Exception("Can't make reciprocal of a frac that equals to null");
        }
        return new Fraction(b,a);
    }

    public Fraction divide(Fraction fraction) throws Exception {
        return this.multiply(fraction.reciprocal());
    }
    public Fraction inversed() throws Exception {
        return new Fraction(BigInteger.ZERO.subtract(a),b);
    }

    public Fraction pow(int n) throws Exception {
        return new Fraction(a.pow(n),b.pow(n));
    }

    public Fraction substract(Fraction fraction) throws Exception {
        return this.add(fraction.inversed());
    }

    public BigInteger getA() {
        return a;
    }

    public BigInteger getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        if(fraction.getA().multiply(b).equals(fraction.getB().multiply(a))){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "[" + a.toString() + "," + b.toString() + "]";
    }
}
