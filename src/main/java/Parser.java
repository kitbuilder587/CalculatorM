import java.util.ArrayList;

public abstract class Parser {
    private ArrayList<String> tokens;



    public Parser(String input) {
        tokens = MathExpressionTokenizer.getTokens(input);
    }

    public abstract Fraction parse() throws Exception;

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }
}
