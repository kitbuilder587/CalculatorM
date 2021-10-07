import java.math.BigInteger;

public class RecursionParser extends Parser{

    int indx = 0;

    public RecursionParser(String input) {
        super(input);
    }

    @Override
    public Fraction parse() throws Exception {
        Fraction res = expression();
        if(indx != getTokens().size()){
            throw new Exception("Error here: " + getTokens().get(indx) + " " + indx);
        }
        return res;
    }

    private Fraction expression() throws Exception {
        Fraction first = term();
        while(indx < getTokens().size()){
            String operator = getTokens().get(indx);
            if(!getTokens().get(indx).equals("+") && !getTokens().get(indx).equals("-")){
                break;
            }else{
                indx++;
            }

            Fraction second = term();
            if(operator.equals("+")){
                first = first.add(second);
            }
            if(operator.equals("-")){
                first = first.substract(second);
            }
        }
        return first;
    }

    private Fraction term() throws Exception {
        Fraction first = exponent();
        while(indx < getTokens().size()){
            String operator = getTokens().get(indx);
            if(!getTokens().get(indx).equals("*") && !getTokens().get(indx).equals("*")){
                break;
            }else{
                indx++;
            }

            Fraction second = exponent();
            if(operator.equals("*")){
                first = first.multiply(second);
            }
            if(operator.equals("/")){
                first = first.divide(second);
            }
        }
        return first;
    }

    private Fraction exponent() throws Exception {
        Fraction first = factor();
        while(indx < getTokens().size()){
            String operator = getTokens().get(indx);
            if(!getTokens().get(indx).equals("^")){
                break;
            }else{
                indx++;
            }

            Fraction second = factor();
            if(operator.equals("^")){
                first = first.pow(second.getA().intValue());
            }
        }
        return first;
    }

    private Fraction factor() throws Exception {
        String currToken = getTokens().get(indx);
        Fraction res;
        if(currToken.equals("(")){
            indx++;
            res = expression();
            if(indx >=  getTokens().size()){
                throw new Exception("Unexpected end");
            }
            if(!getTokens().get(indx).equals(")")){
                throw new Exception(getTokens().get(indx) + " instead of )");
            }
            indx ++;
            return res;
        }
        indx++;
        return new Fraction(new BigInteger(currToken),BigInteger.ONE);
    }
}
