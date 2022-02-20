/** 
 * Class to interpret and compute the result of arithmetic expressions 
 * in INFIX format - 
 */
public class Calculate {

  /** Run short test */
  public static void main(String[] args) {
    if (args.length == 0) {
      // If no arguments passed, print instructions
      System.err.println("Usage:  java Calculate <expr>");
    } else {
      calculate(args[0]);
    }
  }
	
	/** 
   *  Converts the input string to a queue of tokens 
   *  @param input  the string to convert
   *  @return  the queue of tokens
   */
  public static void calculate(String input){
		Postfix.postfixCalculator(Tokenizer.readTokens(input));
	}

}