
public class TokenList_Imp implements TokenList {
	
	private Token[] tokenList;
	private int index;
	private static final int STANDARD_LENGTH = 10;

	public TokenList_Imp() {
		tokenList = new Token[STANDARD_LENGTH];
		index = -1;
	}
	
	public TokenList_Imp(int length) {
		tokenList = new Token[length];
		index = -1;
	}	

	public void add(Token token) {
		index++;		
		if (index == tokenList.length) {
			Token[] tempStack = new Token[tokenList.length * 2];
    			System.arraycopy(tokenList, 0, tempStack, 0, tokenList.length);
    			tokenList = tempStack;
		}
		tokenList[index] = token;
	}

    public void remove(int index) { 
    	Token[] tempArray = new Token[tokenList.length];
    	System.arraycopy(tokenList, 0, tempArray, 0, index-1);
    	System.arraycopy(tokenList, index+1, tempArray, index, tokenList.length - index);
    }

	public void set(int index, Token token) {
		tokenList[index] = token;
	}

	public Token get(int index) {
		return tokenList[index];
	}

	public int size() {
		return index + 1;
	}

}

