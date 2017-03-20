/*
 * Program converts an infix expression
 * into a postfix expression using a stack,
 * and computes and the value of the postfix expression
 * 
 * Source code by Ana Garcia
 * CS3350 - Automata
 * Last modified on 03/20/2017
 * 
 */
import java.util.*;
public class PostFixDemo {	
	public static void main(String[]args){
		String infix = "(1+2)*(3-4)";
		String postfix = infixToPostFix(infix);
		System.out.println("Postfix conversion: " + postfix);
		System.out.println();
		int postfixResult = computePostfix(postfix);
		System.out.println("Postfix result: " + postfixResult);
	}
	
	/*Step 1: If you see a number, add it to the string.
	 * 		  if you see an operation symbol, you push it
	 * 		  into the stack, but before that, you may
	 * 		  need to pop something from the stack.
	 */
	public static String infixToPostFix(String infix){
		 Stack s = new Stack();
		 String postfix= "";
		 for(int i =0; i<infix.length();i++){
			 if(infix.charAt(i) == '('){
				 s.push(infix.charAt(i));
				 System.out.println("Found a (");
				 System.out.println("Top of stack: " + s.peek());
			 }
			 else if(infix.charAt(i)== '+'|| infix.charAt(i)=='-' || infix.charAt(i) == '*' || infix.charAt(i)=='/'){
				 s.push(infix.charAt(i));
				 System.out.println("Found an operator");
				 System.out.println("Top of stack: " + s.peek());
			 }
			 else if(infix.charAt(i) == ')'){
				 System.out.println("Found a )");
				 System.out.println("Top of stack: " + s.peek());
				 if(!s.isEmpty()){
					 System.out.println("Stack is not empty");
					 char operator = (char)s.pop();
					 postfix+=operator;
					 System.out.println("Current postfix: " + postfix);
					 System.out.println();
				 }
			 }
			//it's a number
			 else{
				 System.out.println("Found a number");
				 postfix += infix.charAt(i);
				 System.out.println("Current postfix: " + postfix);
				 System.out.println();
			 }			 
		 }
		 /*We have gone through the entire length of infix.
		  * Now, we need to go through whatever's left in the stack
		  * and pop ('s and )'s and concatenating operators
		  * at the end of postfix
		  */
		 while(!s.isEmpty()){
			 if((char)s.peek() == '(' || (char)s.peek() == ')'){
				 s.pop();
			 }
			 else{
				 char operator = (char)s.pop();
				 postfix += operator;
			 }
		 }
		 return postfix;		
	}
	
	/*Step 2: if we see a number, we push it into the stack
	 * 		  if we see an operation symbol, we pop the top 
	 * 		  two numbers, perform the mathematical operation
	 * 		  and push the result back into the stack
	 */
	public static int computePostfix(String postfix){
		System.out.println("Computing postfix expression: ");
		Stack s = new Stack();
		for(int i=0; i<postfix.length();i++){
			if(postfix.charAt(i)== '+' || postfix.charAt(i) == '-'||postfix.charAt(i)=='*'||postfix.charAt(i)=='/'){
				performOperation(s,postfix.charAt(i));
			}
			else{//it's a number
				//stack contents are chars that need to be cast to Integer
				s.push(Character.getNumericValue((char)postfix.charAt(i)));
				System.out.println("Top of stack: " + s.peek());
			}
		}
		//last item in the stack
		int result = (int)s.pop();
		return result;		
	}
	
	public static void performOperation(Stack s, char operator){
		int operand1;
		int operand2;
		int result;
		switch(operator){
			case '+':
				System.out.println("Performed Addition");
				//Integer types in the stack need to be cast to int
				operand2 = (int)s.pop();
				operand1 = (int)s.pop();
				result = operand1 + operand2;
				System.out.println("Result: " + result);
				s.push(result);
				break;
			case '-':
				System.out.println("Performed Subtraction");
				operand2 = (int)s.pop();
				operand1 = (int)s.pop();
				result = operand1 - operand2;
				System.out.println("Result: " + result);
				s.push(result);
				break;
			case '*':
				System.out.println("Performed Multiplication");
				operand2 = (int)s.pop();
				operand1 = (int)s.pop();
				result = operand1 * operand2;
				System.out.println("result: " + result);
				s.push(result);
				break;
			case '/':
				System.out.println("Performed Division");
				operand2 = (int)s.pop();
				operand1 = (int)s.pop();
				result = operand1/operand2;
				System.out.println("result: " + result);
				s.push(result);
				break;
		}
	}
}
