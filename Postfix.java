import java.util.*;

/** 
 * 	Class to interpret and compute the result of arithmetic expressions 
 * 	in POSTFIX format - 
 *	
 *	@author Anh Nguyen, Sabrina Hatch
 *	@version Spring 2022
 */
public class Postfix {
	
	/** 
	* 	Class to interpret and compute the result of expressions 
	* 	in POSTFIX format - 
	*	
	*		@param 	queueInput	a queue that contains the 
	*												tokens of postfix expressions
	*		@throws	
	*/
	public static void postfixCalculator(ArrayDeque<Object> queueInput) {
		ArrayDeque<Double> stackOfNumber = new ArrayDeque<Double>();

		while (!queueInput.isEmpty()) {
			Object token = queueInput.removeFirst();
			if (token instanceof Double) {
				stackOfNumber.addFirst((Double)token);
			}
			else if (token instanceof Character) {
				double first;
				double second;
				double result;
				Character operator = (Character)token;
				System.out.println(operator);
				first = stackOfNumber.removeFirst();
				second = stackOfNumber.removeFirst();
				if (operator.equals('+')) {
					result = first + second;
					stackOfNumber.addFirst(result);
				} else if (operator.equals('-')) {
					result = second - first;
					stackOfNumber.addFirst(result);
				} else if (operator.equals('*')) {
					result = first * second;
					stackOfNumber.addFirst(result);
				} else if (operator.equals('/')) {
					result = second / first;
					stackOfNumber.addFirst(result);
				} else if (operator.equals('^')) {
					result = Math.pow(second, first);
					stackOfNumber.addFirst(result);
				} else {
					System.out.println("I don't recognize that operation!");
				}
			}
		}
		System.out.println("Answer: " + stackOfNumber.toString());
	}

	/** Run short test */
  public static void main(String[] args) {
    if (args.length==0) {
      System.err.println("Usage:  java Postfix <expr>");
    } else {
			postfixCalculator(Tokenizer.readTokens(args[0]));
    }
  }
}
