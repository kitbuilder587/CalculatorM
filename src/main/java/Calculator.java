public interface Calculator {
    Fraction add(Fraction a,Fraction b) throws Exception;
    Fraction multiply(Fraction a,Fraction b) throws Exception;
    Fraction substract(Fraction a,Fraction b) throws Exception;
    Fraction divide(Fraction a,Fraction b) throws Exception;
    Fraction pow(Fraction a,int n) throws Exception;
}
