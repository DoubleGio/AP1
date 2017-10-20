import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Main implements CalculatorInterface {
    
    private static final String OPERATOR_TOKENS = "+ - * / ^",
			PLUS_TOKEN = "+",
			MINUS_TOKEN = "-",
			MULTIPLY_TOKEN = "*",
			DIVIDE_TOKEN = "/",
			POWER_TOKEN = "^";
    
    private static final int NUMBER_PRECEDENCE = -1,
    		PARENTHESIS_PRECEDENCE = -2;

	private boolean isNumber(String token) {
		try(Scanner in = new Scanner(token)) {
			return in.hasNextInt();
		}
	}
	
	private boolean isOperator(String token) {
		try(Scanner operatorScanner = new Scanner(OPERATOR_TOKENS)) {
			while (operatorScanner.hasNext()) {
				if (token.equals(operatorScanner.next())) {
					return true;
				} 
			}
		}
		return false;
	}
	
	private int whichPrecedence(String token) {
		int presedence = 0;
		switch (token) {
		case PLUS_TOKEN: presedence = 0;
			break;
		case MINUS_TOKEN: presedence = 0;
			break;
		case MULTIPLY_TOKEN: presedence = 1;
			break;
		case DIVIDE_TOKEN: presedence = 1;
			break;
		case POWER_TOKEN: presedence = 2;
			break;
		}
		return presedence;
	}
	
	private boolean isParenthesis(String token) {
		if (token.equals("(") | token.equals(")")) {
			return true;
		} else {
			return false;
		}
	}
	
    public TokenList readTokens(String input) {
    	TokenList temp = new TokenList_Imp();
		Scanner in = new Scanner(input);
		while (in.hasNext()) {
			String token = in.next();
			if (isNumber(token)) {
				temp.add(new Token_Imp(token, Token.NUMBER_TYPE, NUMBER_PRECEDENCE));
			} else if (isOperator(token)) {
				temp.add(new Token_Imp(token, Token.OPERATOR_TYPE, whichPrecedence(token)));
			} else if (isParenthesis(token)) {
				temp.add(new Token_Imp(token, Token.PARENTHESIS_TYPE, PARENTHESIS_PRECEDENCE));
			} else {
				PrintStream out = new PrintStream(System.out);
				out.print("ERROR: unknown character");
				break;
			}
		}
		TokenList result = new TokenList_Imp(temp.size());
		for (int i = 0; i < temp.size(); i++) {
			result.add(temp.get(i));
		}
		in.close();
		return result;
    }

    public Double rpn(TokenList tokens) {
    	DoubleStack stack = new DoubleStack_Imp();
    	double result = 0.0;
    	
    	for (int i = 0; i < tokens.size(); i++){
    		if (tokens.get(i).getType() == Token.NUMBER_TYPE) {
    			Scanner in = new Scanner(tokens.get(i).getValue());
    			stack.push(in.nextDouble());
    			in.close();
    		} else if (tokens.get(i).getType() == Token.OPERATOR_TYPE){
    			stack = performOperation(tokens.get(i), stack);
    		}
    	}
    	result = stack.top();
        return result;
    }
	
    private DoubleStack performOperation(Token operator, DoubleStack stack) {
    	double a = stack.pop();
    	double b = stack.pop();
    	
    	switch (operator.getValue()) {
    	case PLUS_TOKEN: stack.push(a + b);
    		break;
    	case MINUS_TOKEN: stack.push(b - a);
    		break;
    	case MULTIPLY_TOKEN: stack.push(a * b);
    		break;
    	case DIVIDE_TOKEN: stack.push(b / a);
    		break;
    	case POWER_TOKEN: stack.push(Math.pow(b, a));
    		break;
    	}
    	return stack;
    }

    public TokenList shuntingYard(TokenList tokens) {
    	TokenList tokens2 = new TokenList_Imp(tokens.size());
    	TokenStack operatorStack = new TokenStack_Imp();
    	int index = 0;
    	while (index < tokens.size()) {
			Token token = tokens.get(index);
	
			if (token.getType() == Token.NUMBER_TYPE) {
				tokens2.add(token);
			} else if (token.getType() == Token.OPERATOR_TYPE) {
				while (operatorStack.top() != null && operatorStack.top().getPrecedence() >= token.getPrecedence()) {
					tokens2.add(operatorStack.pop());
				}
				operatorStack.push(token);
			}
			if (token.getValue().equals("(")) {
				operatorStack.push(token);
			}
			if (token.getValue().equals(")")) {
				while (operatorStack.top() != null && !(operatorStack.top().getValue().equals("("))) {
					tokens2.add(operatorStack.pop());
				}
				operatorStack.pop();
			}
			index++;
		}
        while (operatorStack.top() != null) {
    		tokens2.add(operatorStack.pop());
    	}
        return tokens2;
    }

    private void start() {
        Scanner in = new Scanner(System.in);
        PrintStream out = new PrintStream(System.out);

        while (in.hasNextLine()) {
        	TokenList tokens = readTokens(in.nextLine());
        	tokens = shuntingYard(tokens);
        	Double result = rpn(tokens);
        	out.printf("%.6f \n", result);
        }
        in.close();
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}
