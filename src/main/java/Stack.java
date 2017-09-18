
public class Stack implements TokenStack {

	private Token[] tokenStack;
	private int topOfStack;
	
	public Stack () {
		tokenStack = new Token[10];
		topOfStack = -1;
	}
	
	public Stack (TokenList tokens) {
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
    			System.arraycopy(tokenStack, 0, tempStack, 0, tokenStack.length - 1);
    			tokenStack = tempStack;
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
		try {
			return tokenStack[topOfStack];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public int size() {
		return topOfStack;
	}

}
