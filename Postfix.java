import java.util.Scanner;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.io.IOException;

/**
 * Class to interpret and compute the result of arithmetic expressions in
 * POSTFIX format -
 * 
 * @author Anh Nguyen, Sabrina Hatch
 * @version Spring 2022
 */
public class Postfix {

  /** Pattern that matches on words */
  public static final String WORD = "[a-zA-Z]*\\b";

  /** Pattern that matches on arithmetic symbols */
  public static final String SYMBOL = "[^\\w]";

  /**
   * Class to read POSTFIX format and store tokens in a queue
   *
   * @param input string of postfix expression
   * @return a queue that contains the tokens of postfix expression
   */
  public static ArrayDeque<Object> readTokens(String input) {
    ArrayDeque<Object> tokens = new ArrayDeque<Object>();

    Scanner scanner = new Scanner(new StringReader(input));

    scanner.useDelimiter("(\\s+" // whitespace
        + "|(?<=[a-zA-Z])(?=[^a-zA-Z])" // word->non-word
        + "|(?<=[^a-zA-Z])(?=[a-zA-Z])" // non-word->word
        + "|(?<=[^0-9\\056])(?=[0-9\\056])" // non-number->number
        + "|(?<=[0-9\\056])(?=[^0-9\\056])" // number->non-number
        + "|(?<=[^\\w])(?=[^\\w]))");

    while (scanner.hasNext()) {
      if (scanner.hasNextDouble()) {
        tokens.add(scanner.nextDouble());
      } else if (scanner.hasNext(WORD)) {
        tokens.add(scanner.next(WORD));
      } else if (scanner.hasNext(SYMBOL)) {
        tokens.add(scanner.next(SYMBOL).charAt(0)); 
      } else {
        tokens.add(scanner.next());
      }
    }
    scanner.close();
    return tokens;
  }

  /**
   * Class to interpret and compute the result of expressions in POSTFIX format -
   * 
   * @param queueInput a queue that contains the tokens of postfix expressions
   * @throws 
   */
   
  public static void postfixCalculator(ArrayDeque<Object> queueInput) {
    
		ArrayDeque<Double> stackOfNumber = new ArrayDeque<Double>();
    
		try{
			if(queueInput.isEmpty())
			{
				throw new IOException("User did not enter an input");
			}
		} catch (IOException e) {
			System.out.println("ERROR: Empty Input. Please restart the program and use proper Postfix notation.");
		}
		
    while (!queueInput.isEmpty()) {
      Object token = queueInput.removeFirst();
      if (token instanceof Double) {
        stackOfNumber.addFirst((Double) token); 
      } 
      
      else if (token instanceof Character) {
				if (stackOfNumber.size() <= 1) 
        {
					throw new RuntimeException("Malformed Input. Please restart the program and use proper Postfix notation.");
				
        double result;
        Character operator = (Character) token;
        double first = stackOfNumber.removeFirst();
        double second = stackOfNumber.removeFirst();
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
        } 
        else {
          throw new ArithmeticException("ERROR: Improper Postfix Expression.");
        }
      } else {
				throw new ArithmeticException("ERROR: Improper Postfix Expression.");
			}
    }
		
		double ans = stackOfNumber.pop();
			if (stackOfNumber.isEmpty()) {
				System.out.println("Answer: " + ans);
			} else {
				throw new ArithmeticException("ERROR: Improper Postfix Expression.");
			}
  }

  /** Run short test */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("Usage:  java Postfix <expr>");
    } else {
      postfixCalculator(readTokens(args[0]));
    }
  }
}
