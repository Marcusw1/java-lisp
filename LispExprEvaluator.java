/************************************************************************************
 *
 *  		CSC220 Programming Project#2
 *  
 * Due Date: 23:59p.m. , Monday, 11/04/2013  
 *           Upload LispExprEvaluator.java to i-learn 
 *
 * Specification: 
 *
 * Taken from Project 6, Chapter 5, Page 137
 * I have modified specification and requirements of this project
 */
 //http://www.gigamonkeys.com/book/        (see chap. 10)
 //http://joeganley.com/code/jslisp.html   (GUI)
 /*
 * In the language Lisp, each of the four basic arithmetic operators appears 
 * before an arbitrary number of operands, which are separated by spaces. 
 * The resulting expression is enclosed in parentheses. The operators behave 
 * as follows:
 *
 * (+ a b c ...) returns the sum of all the operands, and (+ a) returns a.
 *
 * (- a b c ...) returns a - b - c - ..., and (- a) returns -a. 
 *
 * (* a b c ...) returns the product of all the operands, and (* a) returns a.
 *
 * (/ a b c ...) returns a / b / c / ..., and (/ a) returns 1 / a. 
 *
 * Note: each operator must have at least one operand
 *
 * You can form larger arithmetic expressions by combining these basic 
 * expressions using a fully parenthesized prefix notation. 
 * For example, the following is a valid Lisp expression:
 *
 * 	(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)))
 *
 * This expression is evaluated successively as follows:
 *
 *	(+ (- 6) (* 2 3 4) (/ 3 1 -2))
 *	(+ -6 24 -1.5)
 *	16.5
 *
 * Requirements:
 *
 * - Design and implement an algorithm that uses Java API stacks to evaluate a 
 *   Valid Lisp expression composed of the four basic operators and integer values. 
 * - Valid tokens in an expression are '(',')','+','-','*','/',and positive integers (>=0)
 * - In case of errors, your program must throw LispExprEvaluatorException
 * - Display result as floting point number with at 2 decimal places
 * - Negative number is not a valid "input" operand, e.g. (+ -2 3) 
 *   However, you may create a negative number using parentheses, e.g. (+ (-2)3)
 * - There may be any number of blank spaces, >= 0, in between tokens
 *   Thus, the following expressions are valid:
 *   	(+   (-6)3)
 *   	(/(+20 30))
 *
 * - Must use Java API Stack class in this project.
 *   Ref: http://docs.oracle.com/javase/7/docs/api/java/util/Stack.html
 * - Must throw LispExprEvaluatorException to indicate errors
 * - Must not add new or modify existing data fields
 * - Must implement these methods : 
 *
 *   	public LispExprEvaluator()
 *   	public LispExprEvaluator(String inputExpression) 
 *      public void reset(String inputExpression) 
 *      public double evaluate()
 *      private void evaluateCurrentOperation()
 *
 * - You may add new private methods
 *
 *************************************************************************************/

package PJ2;
import java.util.*;

public class LispExprEvaluator
{
    // Current input Lisp expression
    private String inputExpression;
    // Main expression stack & current operation stack, see algorithm in evaluate()
    private Stack<Object> thisExprStack;
    private Stack<Double> thisOpStack;

    
    // default constructor
    // set inputExpr to "" 
    // create stack objects
    public LispExprEvaluator()
    {
        // add statements 
        inputExpression = "";
        thisExprStack = new Stack ();
        thisOpStack = new Stack ();
        
    }

    // constructor with an input expression 
    
    // create stack objects
    public LispExprEvaluator(String inputExpression) 
    {
	// add statements
        if(inputExpression == null){ 
            throw new LispExprEvaluatorException ();
        }else {
         this.inputExpression = inputExpression;
         thisExprStack = new Stack <Object>();
         thisOpStack = new Stack <Double> ();
        }
    }
    
    //new method to check balance ( to handle extra parantheses )
   
    
    
    
    // set inputExpr to inputExpression 
    // clear stack objects
    public void reset(String inputExpression) 
    {
        if(inputExpression == null){
            throw new LispExprEvaluatorException();
        }
        this.inputExpression = inputExpression;
		thisExprStack.clear();
        thisOpStack.clear();   
    }

           
    private void evaluateCurrentOperation()
    {
      
      String currentOp ; 
      boolean num = true ;
      
      do{
          currentOp = (String.valueOf (thisExprStack.pop()));
          try {
              Double  number = Double.parseDouble(currentOp);
              thisOpStack.push(number);
            
           } catch(NumberFormatException nfe) {
               num = false;
           
            }
      
          
      } while (num) ;
    
        double result;
        switch (currentOp) {
                
            case "*":
                result = thisOpStack.pop();
                System.out.println ("result: " + result);
                while(!thisOpStack.isEmpty()){
                    result *= thisOpStack.pop();
                    
                }
                break;
                
            case "/":
                
                result = thisOpStack.pop();
               
                while(!thisOpStack.isEmpty()){
                     result /= thisOpStack.pop();    
                     
                
                    }
                break;
                
            case "+":
                result = thisOpStack.pop();
                
                while(!thisOpStack.isEmpty()){
                    result += thisOpStack.pop();
                    
                }
                break;
                
            case "-":
                if( thisOpStack.size() == 1){
                    result = -thisOpStack.pop();
                    while(!thisOpStack.isEmpty()){
                    result -= thisOpStack.pop();
                    
                    }
                } else {
                     result = thisOpStack.pop();
                
                while(!thisOpStack.isEmpty()){
                    result -= thisOpStack.pop();
                    
                    }
                
                }
                break;
                
            default:
                result = thisOpStack.pop();
                break;
        }
        
        thisExprStack.push(result); 
            
    }
 

	//method unfinished , check balanced to deal with excessive parentheses 
    public boolean checkBalance () {
        
       Scanner inputCheckBalanceScanner = new Scanner(this.inputExpression);
       
                 
       
           Stack <Object> openParantheses = new Stack();
           Stack  <Object> closeParantheses = new Stack();
           
           boolean balanced = false;
           
          
            while (inputCheckBalanceScanner.hasNext()) {
              
              String token = inputCheckBalanceScanner.next(); 
              char   item2 = token.charAt(0);
              switch(item2){
                 
                  case '(': 
                      openParantheses.push(item2);
                      
                      break ;
                  case ')': 
                      closeParantheses.push(item2);
                      
                      break ;
              }
                        
              }
                System.out.println (openParantheses.size()) ;
                System.out.println (closeParantheses.size()) ;
              
              if (openParantheses.size() == closeParantheses.size()) {
                  balanced = true;  
                  return balanced;
          
             } else {
                  
              
                return balanced;
              }
      }
    
    
    

    public double evaluate()
    {
        
        
        // only outline is given...
        // you need to add statements/local variables
        // you may delete or modify any statements in this method

        // use scanner to tokenize inputExpression
        Scanner inputExprScanner = new Scanner(this.inputExpression);
        
        // Use zero or more white space as delimiter,
        // which breaks the string into single character tokens
        inputExprScanner = inputExprScanner.useDelimiter("\\s*");
        
        
        // Step 1: Scan the tokens in the string.
        while (inputExprScanner.hasNext())
        {
		 
     	    // Step 2: If you see an operand, push operand object onto the thisExprStack
            if (inputExprScanner.hasNextInt())
            {
              
                // This force scanner to grab all of the digits
                // Otherwise, it will just get one char
                String dataString = inputExprScanner.findInLine("\\d+");
                
                thisExprStack.push (Double.parseDouble(dataString)) ; 
                
                // more ...
            }
            else
            {
                // Get next token, only one char in string token
                String aToken = inputExprScanner.next();       
                char item = aToken.charAt(0);
                
                
                switch (item)
                {
                    
                    
               case '(' :
                   
                   String aToken2 = inputExprScanner.next();       
                   char nextItem = aToken2.charAt(0);
                   
                   if (nextItem == '+' ^ nextItem == '-' ^ nextItem == '/' ^ nextItem == '*') { 
                     thisExprStack.push(nextItem);
                     
                     
                   break;
                   
                   } else {
                       
                       throw new LispExprEvaluatorException(nextItem + " invalid ");
                   }
                   
                   
                   
                case ')':
                    
                    evaluateCurrentOperation();
                    break;
                    
                    
                   
     		    // Step 3: If you see "(", next token should be an operator
     		    // Step 4: If you see an operator, push operator object onto the thisExprStack
     		    // Step 5: If you see ")"  // steps in evaluateCurrentOperation() :
                    default:  // error
                        throw new LispExprEvaluatorException(item + " is not a legal expression operator");
                } // end switch
            } // end else
        } // end while
        
        // Step 9: If you run out of tokens, the value on the top of thisExprStack is
        //         is the result of the expression.
        //
        //         return result
	 double result = (Double)thisExprStack.pop();
         return result;
    }
    

    //=====================================================================
    // DO NOT MODIFY ANY STATEMENTS BELOW
    //=====================================================================

    // This static method is used by main() only
    private static void evaluateExprTest(String s, LispExprEvaluator expr)
    {
        Double result;
        System.out.println("Expression " + s);
	expr.reset(s);
        result = expr.evaluate();
        System.out.printf("Result %.2f\n", result);
        System.out.println("-----------------------------");
    }
    // define few test cases, exception may happen
    public static void main (String args[])
    {
        LispExprEvaluator expr= new LispExprEvaluator();
        String test1 = "(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)))";
        String test2 = "(+ (- 632) (* 21 3 4) (/ (+ 32) (* 1) (- 21 3 1)))";
        String test3 = "(+ (/ 2) (* 2) (/ (+ 1) (+ 1) (- 2 1 )))";
        String test4 = "(+ (/2))";
        String test5 = "(+ (/2 3 0))";
        String test6 = "(+ (/ 2) (* 2) (/ (+ 1) (+ 3) (- 2 1 ))))";
	evaluateExprTest(test1, expr);
	evaluateExprTest(test2, expr);
	evaluateExprTest(test3, expr);
	evaluateExprTest(test4, expr);
	evaluateExprTest(test5, expr);
	evaluateExprTest(test6, expr);
    }
}

//DONT WORK:
//doesnt compute correct >> ( * ( / 100 5 ) 4 ) 10
//dont catch exception  >>  (+ (/ 2) (* 2) (/ (+ 1) (+ 3) (- 2 1 ))))