
public class DoubleStack_Imp implements DoubleStack {
	
	private double[] doubleStack;
	private int topOfStack;
	
	public DoubleStack_Imp () {
		doubleStack = new double[10];
		topOfStack = -1;
	}
	
    public void push(Double element) {
    	topOfStack ++;
    	if (topOfStack == doubleStack.length) {
    		double[] tempStack = new double[doubleStack.length * 2];
    		System.arraycopy(doubleStack, 0, tempStack, 0, doubleStack.length - 1);
    		doubleStack = tempStack;
    	}
	doubleStack[topOfStack] = element;
    }

    public Double pop() {
    	if (topOfStack <= -1) {
			//something error
		}
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
