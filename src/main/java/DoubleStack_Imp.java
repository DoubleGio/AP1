
public class DoubleStack_Imp implements DoubleStack {
	
	private double[] doubleStack;
	private int topOfStack;
	private static final int STANDARD_LENGTH = 10;
	
	public DoubleStack_Imp () {
		doubleStack = new double[STANDARD_LENGTH];
		topOfStack = -1;
	}
	
    public void push(Double element) {
    	topOfStack ++;
    	if (topOfStack == doubleStack.length) {
    		double[] tempStack = new double[doubleStack.length * 2];
    		System.arraycopy(doubleStack, 0, tempStack, 0, doubleStack.length);
    		doubleStack = tempStack;
    	}
    	doubleStack[topOfStack] = element;
    }

    public Double pop() {
		topOfStack--;
		return doubleStack[topOfStack + 1];
    }

    public Double top() {
    	try {
    		return doubleStack[topOfStack];
    	} catch (ArrayIndexOutOfBoundsException e) {
    		return null;
    	}
    }

    public int size() {
    	return topOfStack + 1;
    }
}
