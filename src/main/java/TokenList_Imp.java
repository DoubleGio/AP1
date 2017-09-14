
public class TokenList_Imp implements TokenList {
	
	private Token[] tokenList;  //ollafdsfebve
	private int index;	//edit4
	
	public TokenList_Imp(int length) {
		tokenList = new Token[length];	//string length
		index = 0;
	}

	public void add(Token token) {
		tokenList[index] = token;
		index++;
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
		return tokenList.length;
	}

}

