public class Token_Imp implements Token {

	private String token;
	private int type;
	private int precedence;
	
	public Token_Imp(String s, int t, int p) {
		token = s;
		type = t;
		precedence = p;
	}
	
	public String getValue() {
		return token;
	}

	public int getType() {
		return type;
	}

	public int getPrecedence() {
		return precedence;
	}

}
