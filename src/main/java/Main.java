import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements CalculatorInterface {
    
    private static final String OPERATOR_TOKENS = "+ - * / ^";
    private static final String PLUS_TOKEN = "+";
    private static final String MINUS_TOKEN = "-";
    private static final String MULTIPLY_TOKEN = "*";
    private static final String DIVIDE_TOKEN = "/";
    private static final String POWER_TOKEN = "^";

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
	
	private int whichPresedence(String token) {
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
    	TokenList result = new TokenList_Imp(input.replaceAll("\\s+","").length());
		Scanner in = new Scanner(input);
		while (in.hasNext()) {
			String token = in.next();
			int type;
			int precedence;
			int extra_precedence = 0;
			if (isNumber(token)) {
				type = 1;
				precedence = -1 + extra_precedence;
			} else if (isOperator(token)) {
				type = 2;
				precedence = whichPresedence(token) + extra_precedence;
			} else if (isParenthesis(token)) {
				type = 3;
				precedence = -2;
				if (token.equals("(")) {
				    extra_precedence++;
				} else if (token.equals(")")) {
				    extra_precedence--;
				}
			} else {
				PrintStream out = new PrintStream(System.out);
				out.print("ERROR: unknown character");
				//System.exit(1);
				break;
			}
			result.add(new Token_Imp(token, type, precedence));
		}
		in.close();
		return result;
    }

    
    public Double rpn(TokenList tokens) {
    	DoubleStack stack = new DoubleStack_Imp();		//TODO: stack van tokens --> doublestack
    	double result = 0.0;
    	
    	for (int i = 0; i < tokens.size(); i++){
    		if (tokens.get(i).getType() == 1) {
    			Scanner in = new Scanner(tokens.get(i).getValue());
    			stack.push(in.nextDouble());
    			in.close();
    		} else if (tokens.get(i).getType() == 2){
    			stack = performOperation(tokens.get(i), stack);
    		}
    	}
    	if (stack.size() == 1) {
    		result = stack.top();
    	} else {
    		//something error
    	}
        return result;
    }
	
    private DoubleStack performOperation(Token operator, DoubleStack stack) {
    	double a = stack.pop();
    	double b = stack.pop();
    	
    	if (operator.getValue().equals(PLUS_TOKEN)) {
    		stack.push(a + b);
    	} else if (operator.getValue().equals(MINUS_TOKEN)) {
    		stack.push(a - b);
    	} else if (operator.getValue().equals(MULTIPLY_TOKEN)) {
    		stack.push(a * b);
    	} else if (operator.getValue().equals(DIVIDE_TOKEN)) {
     		stack.push(a / b);
     	} else if (operator.getValue().equals(POWER_TOKEN)) {
    		stack.push((double)((int)a ^ (int)b)); // pici lelijk
    	}
    	return stack;
    }

    public TokenList shuntingYard(TokenList tokens) {
    	TokenList tokens2 = new TokenList_Imp(tokens.size());
    	Stack operatorStack = new Stack();
    	int index = 0;
    	while (index < tokens.size()) {
		Token token = tokens.get(index);

		if (token.getType() == 1) {
			tokens2.add(token);
		} else if (token.getType() == 2) {
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
        // Create a scanner on System.in
        
        // While there is input, read line and parse it.
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}
