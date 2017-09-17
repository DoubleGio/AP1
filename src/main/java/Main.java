import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements CalculatorInterface {
    
    private static final String OPERATOR_TOKENS = "+-*/^";

	private boolean isNumber(String token) {
		try(Scanner in = new Scanner(token)) {
			return in.hasNextInt();
		}
	}
	
	private boolean isOperator(String token) {
		try(Scanner in = new Scanner(token)) {
			try(Scanner operatorScanner = new Scanner(OPERATOR_TOKENS)) {
				while (operatorScanner.hasNext()) {
					if (in.equals(operatorScanner.next())) {
						return true;
					} 
				}
				return false;
			}
		}
	}
	
	private int whichPresedence(String token) {
		int presedence = 0;
		switch (token) {
			case "+": presedence = 0;
				break;
			case "-": presedence = 0;
				break;
			case "*": presedence = 1;
				break;
			case "/": presedence = 1;
				break;
			case "^": presedence = 2;
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
				precedence = 3;
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
    	Stack stack = new Stack(tokens);
        return null;
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
        		while (operatorStack.top().getPrecedence() >= token.getPrecedence()) {
        			tokens2.add(operatorStack.pop());
        		}
        		operatorStack.push(token);
        	}
        	if (token.getValue().equals("(")) {
        		operatorStack.push(token);
        	}
        	if (token.getValue().equals(")")) {
        		while (!operatorStack.top().equals("(")) {
        			tokens2.add(operatorStack.pop());
        		}
        		operatorStack.pop();
        	}
        	while (!operatorStack.top().equals(null)) {
        		tokens2.add(operatorStack.pop());
        	}
        	index++;
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
