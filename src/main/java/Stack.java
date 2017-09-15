
public class Stack implements TokenStack {

	private Token[] tokenStack;
	private int topOfStack;
	
	public Stack (TokenList tokens) {		//FIX DIT ofzo
		tokenStack = new Token[tokens.size()];
		topOfStack = -1;
		while (topOfStack < tokens.size()) {
			topOfStack++;
        	Token token = tokens.get(topOfStack);
        	int type = token.getType();
        	if (type == 1) {
        		tokenStack[topOfStack] = token;
        	} else if (type == 2) {
        		Token token2 = tokens.get(topOfStack + 1);
        		if (token2.getType() == 3) {
        			
        		}
        		tokenStack[topOfStack] = token2;
        		topOfStack++;
        		tokenStack[topOfStack] = token;
        	} else {	//type = 3		3x(4-5)	--> 3 4 5 - x
        		
        	}
        }
	}

	public void push(Token token) {
		topOfStack++;
		if (topOfStack == tokenStack.length) {
			//verdubbel stack grootte
		}
		tokenStack[topOfStack] = token;
	}

	public Token pop() {
		if (topOfStack <= -1) {
			//something error
		}
		topOfStack--;
		return tokenStack[topOfStack + 1];
	}

	public Token top() {
		if (topOfStack <= -1) {
			//something error
		}
		return tokenStack[topOfStack];
	}

	public int size() {
		return topOfStack;
	}

}
