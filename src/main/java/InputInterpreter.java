import java.awt.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

public class InputInterpreter {

    Calculator calculator;

    public InputInterpreter(Calculator calculator) {
        this.calculator = calculator;
    }

    private  boolean isCharAnOperator(char c){
        return c=='*' || c=='+' || c=='-' || c=='/' || c=='^';
    }

    private boolean isBracketsCorrect(String s){
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                stack.push('(');
            }
            if(s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private boolean isStringWitoutMistakes(String s){
        for(int i=0;i<s.length();i++){
            if(!Character.isDigit(s.charAt(i)) && !isCharAnOperator(s.charAt(i)) && s.charAt(i) != '(' && s.charAt(i) != ')'){
                return false;
            }
        }

        if(isCharAnOperator(s.charAt(0))){
            return false;
        }

        for(int i=1;i<s.length();i++){
            if(isCharAnOperator(s.charAt(i)) && isCharAnOperator(s.charAt(i-1))){
                return false;
            }
        }

        if(!isBracketsCorrect(s)) return false;

        return true;
    }

    public String refactorStringWithoutMistakes(String s){
        if(s.charAt(0) == '-' ){
            s = "0" + s;
        }
        StringBuilder num = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(Character.isDigit(s.charAt(i))){
                num.append(s.charAt(i));
            }else if(i!=0 && s.charAt(i-1) == ')'){
                res.append(s.charAt(i));
            }else if(s.charAt(i) != '('){
                res.append("[");
                if(num.length() == 0) res.append("0");
                else res.append(num.toString());
                res.append(",1]").append(s.charAt(i));
                num = new StringBuilder();
            }else{
                res.append(s.charAt(i));
                num = new StringBuilder();
            }
        }
        res.append("[").append(num.toString());
        res.append(",1]");
        return res.toString().replace("()","");
    }

    public Fraction getTheBiggestNumberToTheLeft(String s,int k) throws Exception {
            StringBuilder res = new StringBuilder();
            for(int i=k;i>=0;i--){
                if(Character.isDigit(s.charAt(i))  || s.charAt(i) == ',' || s.charAt(i) == '-'){
                    res.append(s.charAt(i));
                }else {
                    break;
                }
            }
            String[] frac = res.reverse().toString().split(",");
            return new Fraction(new BigInteger(frac[0].replace("[","")),new BigInteger(frac[1].replace("]","")));
        }

    public Fraction getTheBiggestNumberToTheRight(String s,int k) throws Exception {
        StringBuilder res = new StringBuilder();
        for(int i=k;i<s.length();i++){
            if(Character.isDigit(s.charAt(i)) || s.charAt(i) == ',' || s.charAt(i) == '-'){
                res.append(s.charAt(i));
            }else {
                break;
            }
        }
        String[] frac = res.toString().split(",");
        return new Fraction(new BigInteger(frac[0].replace("[","")),new BigInteger(frac[1].replace("]","")));
    }

    private boolean isCharAnOperation(char c, char[] operations){
        for(int i=0;i<operations.length;i++){
            if(c == operations[i]){
                return true;
            }
        }
        return false;
    }

    public String calculateAll(String s,char... operations) throws Exception {
        StringBuilder res = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(isCharAnOperation(s.charAt(i),operations) && (i == 0 || s.charAt(i-1) != '[')){
                res = new StringBuilder();
                char operation = s.charAt(i);
                Fraction a = getTheBiggestNumberToTheLeft(s,i-2);
                Fraction b = getTheBiggestNumberToTheRight(s,i+2);
                if(b.getA().toString().length() >8  && operation == '^'){
                    throw new Exception("The number is toooo big");
                }
                if(!b.getB().equals(BigInteger.ONE) && operation == '^'){
                    throw new Exception("Can't pow into irrational exponent");
                }
                res.append(s.substring(0, i - a.toString().length()));

                switch (operation){
                    case '^': res.append(calculator.pow(a,b.getA().intValue()));
                        break;
                    case '*': res.append(calculator.multiply(a,b));
                        break;
                    case '/': res.append(calculator.divide(a,b));
                        break;
                    case '+': res.append(calculator.add(a,b));
                        break;
                    case '-': res.append(calculator.substract(a,b));
                        break;
                }

                int l = res.length();
                res.append(s.substring(i + b.toString().length()+1));
                i = l-1;
                s =res.toString();
            }
        }
        return s;
    }

    public Fraction calculateResultWithoutBrackets(String s) throws Exception {
        s = calculateAll(s,'^');
        s = calculateAll(s,'*','/');
        s = calculateAll(s,'+','-');
        String[] frac = s.split(",");
        return new Fraction(new BigInteger(frac[0].replace("[","")), new BigInteger(frac[1].replace("]","")));
    }

    public Point getIndexesOfFirstBracket(String s) throws Exception {
        Stack<Character> bracketsStack = new Stack<>();
        Point res = new Point(-1,-1);
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                if(bracketsStack.isEmpty()){
                    res.x = i;
                }
                bracketsStack.push('(');
            }
            if(s.charAt(i) == ')'){
                bracketsStack.pop();
                if(bracketsStack.isEmpty()){
                    res.y = i;
                }
            }
        }
        if(res.y == -1){
            throw new Exception("String doesn't have brackets");
        }
        return res;
    }


    public Fraction calculateResultRecursive(String s) throws Exception {
        if(!s.contains("(")){
            return calculateResultWithoutBrackets(s);
        }
        Point indexes = getIndexesOfFirstBracket(s);
        Fraction inBrackets = calculateResultRecursive(s.substring(indexes.x+1,indexes.y));
        return calculateResultRecursive(s.substring(0,indexes.x) + inBrackets.toString() + s.substring(indexes.y + 1));
    }

    public Fraction calculateResult(String s) throws Exception {
        s = s.replaceAll("\\s","");
        if(s.charAt(0) == '-') s = "0" + s;
        if(isStringWitoutMistakes(s)) {
            s = refactorStringWithoutMistakes(s);
            return calculateResultRecursive(s);
        }else{
            throw new Exception("Interpretation error");
        }
    }


}
