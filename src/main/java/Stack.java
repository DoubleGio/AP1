
public class Stack implements TokenStack {

	private Token[] tokenStack;
	private int topOfStack;
	
	public Stack () {
		tokenStack = new Token[10];
		topOfStack = -1;
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
