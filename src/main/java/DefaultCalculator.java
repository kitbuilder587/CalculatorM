public class DefaultCalculator implements Calculator{
    @Override
    public Fraction add(Fraction a, Fraction b) throws Exception {
        return a.add(b);
    }

    @Override
    public Fraction multiply(Fraction a, Fraction b) throws Exception {
        return a.multiply(b);
    }

    @Override
    public Fraction substract(Fraction a, Fraction b) throws Exception {
        return a.substract(b);
    }

    @Override
    public Fraction divide(Fraction a, Fraction b) throws Exception {
        return a.divide(b);
    }

    @Override
    public Fraction pow(Fraction a, int n) throws Exception {
        return a.pow(n);
    }
}
