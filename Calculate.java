import java.util.Scanner;
import java.io.StringReader;
import java.util.ArrayDeque;

/** 
 * Class to interpret and compute the result of arithmetic expressions 
 * in INFIX format - 
 */
public class Calculate {

	/** Pattern that matches on words */
  public static final String WORD = "[a-zA-Z]*\\b";

	/** Pattern that matches on arithmetic symbols */
  public static final String SYMBOL = "[^\\w]";

	/** Pattern that matches on binary operator */
	public static final String OPERATOR = "[\\+\\-\\*\\/\\^\\%]";

	/** Pattern that matches on constant */
	public static final String CONSTANT = "pi";

	/** Stack that stores the operator */
  public static ArrayDeque<Character> stackOp = new ArrayDeque<Character>();

	/** Stack that stores output postfix tokens */
	public static ArrayDeque<Object> tokens = new ArrayDeque<Object>();

	/** 
	*	Determining the operator priority level.
	*	Level 1 is + and -. 
	*	Level 2 is * and /.
	*	Level 3 is ^.
	*
	* @param	c	the operator to be check for priority level.
	* @return		integer indicates the priority level.
	*	@throws	ArithmeticException	if the input is not a correct operator.
	*/
	public static int checkLevel(char c) {
		int level = -1;
		if (c == '+' || c == '-') {
			level = 1; 
		} else if (c == '*' || c == '/') {
			level = 2; 
		} else if (c == '^') {
			level = 3; 
		} 
		return level;
	}

	/** 
	* 	Comparing the operator priority level.
	*
	* 	@param	op		the operator to be compared for priority level.
	*		@param	peak	the operator to be compared for priority level with *									peek.
	*  	@return				<code>true</code> if the image is completely 
	*             		loaded and was painted successfully; 
	*             		<code>false</code> otherwise.	
	*/
	public static boolean checkPriority(char op, char peek) {
		boolean value = false;
		int levelNewOp = checkLevel(op);
		int levelStackPeek = checkLevel(peek);
		if (levelNewOp <= levelStackPeek && op != '^'){
			value = true;
		}
		return value; 
	}
  
  /** 
   *  Converts the input string to a queue of tokens.
	 * 
   *  @param	input  			the string of infix notation to convert.
   *  @return	ArrayDeque 	the queue of tokens in postfix notation.
   */
  public static ArrayDeque<Object> readTokens(String input) {

		// Read input
    Scanner scanner = new Scanner(new StringReader(input));

    scanner.useDelimiter
      ("(\\s+"                            // whitespace
      +"|(?<=[a-zA-Z])(?=[^a-zA-Z])"      // word->non-word
      +"|(?<=[^a-zA-Z])(?=[a-zA-Z])"      // non-word->word
      +"|(?<=[^0-9\\056])(?=[0-9\\056])"  // non-number->number
      +"|(?<=[0-9\\056])(?=[^0-9\\056])"  // number->non-number
      +"|(?<=[^\\w])(?=[^\\w]))");        // symbol->symbol

		// Read each token
    while (scanner.hasNext()) { 
	
			// If token is a number
      if (scanner.hasNextDouble()) {
        tokens.addLast(scanner.nextDouble());
      } 

			// If token is a binary operator
			else if (scanner.hasNext(OPERATOR)) {
				char c = scanner.next(OPERATOR).charAt(0);
				while (!stackOp.isEmpty() && checkPriority(c,stackOp.peek())) {
					tokens.addLast(stackOp.removeFirst()); 
				}
        stackOp.addFirst(c);  
      } 
			
			// If token is a left parenthese
      else if (scanner.hasNext("\\(")) {
        stackOp.addFirst(scanner.next("\\(").charAt(0));
      }

			// If token is a right parenthese
      else if (scanner.hasNext("\\)")) { 
				scanner.next("\\)");
				while (!stackOp.isEmpty() && stackOp.peek() != '(') {
					tokens.addLast(stackOp.pop()); 
				}
				try {
					stackOp.pop();
				} catch (Exception e) {
      		System.out.println("There are mismatched parentheses.");
				}
      }

			// If token is a constant
			else if (scanner.hasNext(CONSTANT)) {
				// if there are more constant, add a function to find which constant to use
				scanner.next(CONSTANT);
				tokens.addLast(3.14);
			}
			
			// If token is outside of scope 
			else {
				throw new ArithmeticException("ERROR: Invalid input. Please don't include non numeric or non operative symbols.");
			}
    }

		scanner.close();
		
		// No more token to read
		while (!stackOp.isEmpty()){
			if (stackOp.peek() == '(')
      {
      	throw new ArithmeticException("ERROR: There are mismatched parentheses.");
      }
			tokens.addLast(stackOp.pop());
		}
		
    return tokens;
  }

	/** 
   *  Converts the input string to a queue of tokens 
   *  @param input  the string to convert
   *  @return  the queue of tokens
   */
  public static void calculate(String input){
		Postfix.postfixCalculator(readTokens(input));
	}

  /** Run short test */
  public static void main(String[] args) {
    if (args.length == 0) {
      // If no arguments passed, print instructions
      System.err.println("Usage:  java Calculate <expr>. Please retry with a valid input.");
    } else {
      calculate(args[0]);
    }
  }

}