import java.util.ArrayList;

public class MathExpressionTokenizer {

    public static boolean isCharAnOperator(char c){
        return c=='*' || c=='+' || c=='-' || c=='/' || c=='^';
    }

    public static ArrayList<String> getTokens(String input){
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);
            if(isCharAnOperator(c) || c == '(' || c == ')'){

                if(!currentToken.toString().equals("")){
                    tokens.add(currentToken.toString());
                    currentToken = new StringBuilder();
                }

                tokens.add(String.valueOf(c));
            }else if(Character.isDigit(c)){
                currentToken.append(c);
            }
        }
        if(!currentToken.toString().equals("")){
            tokens.add(currentToken.toString());
        }
        return tokens;
    }
}
