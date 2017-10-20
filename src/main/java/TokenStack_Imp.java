
public class TokenStack_Imp implements TokenStack {

	private Token[] tokenStack;
	private int topOfStack;
	private static final int STANDARD_LENGTH = 10;
	
	public TokenStack_Imp () {
		tokenStack = new Token[STANDARD_LENGTH];
		topOfStack = -1;
	}
	
	public TokenStack_Imp (TokenList tokens) {
		tokenStack = new Token[tokens.size()];
		topOfStack = -1;
		for (int i = 0; i < tokens.size(); i++) {
			topOfStack++;
			tokenStack[topOfStack] = tokens.get(i);
		}
	}
	
	public void push(Token token) {
		topOfStack++;
		if (topOfStack == tokenStack.length) {
			Token[] tempStack = new Token[tokenStack.length * 2];
    			System.arraycopy(tokenStack, 0, tempStack, 0, tokenStack.length);
    			tokenStack = tempStack;
		}
		tokenStack[topOfStack] = token;
	}

	public Token pop() {
		topOfStack--;
		return tokenStack[topOfStack + 1];
	}

	public Token top() {
		try {
			return tokenStack[topOfStack];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public int size() {
		return topOfStack + 1;
	}

}
