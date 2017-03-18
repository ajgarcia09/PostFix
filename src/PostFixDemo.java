/*
 * Program converts an infix expression
 * into a postfix expression using a stack,
 * and computes and the value of both expressions
 */
import java.util.*;
public class PostFixDemo {	
	public static void main(String[]args){
		String infix = "(1+2)*(3-4)";
		String postfix = infixToPostFix(infix);
		System.out.println("Postfix conversion: " + postfix);
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
				 System.out.println("found a (");
				 System.out.println("Top of stack: " + s.peek());
			 }
			 else if(infix.charAt(i)== '+'|| infix.charAt(i)=='-' || infix.charAt(i) == '*' || infix.charAt(i)=='/'){
				 s.push(infix.charAt(i));
				 System.out.println("found an operator");
				 System.out.println("Top of stack: " + s.peek());
			 }
			 else if(infix.charAt(i) == ')'){
//				 s.push(infix.charAt(i));
//				 s.pop();
				 System.out.println("found a )");
				 System.out.println("Top of stack: " + s.peek());
				 if(!s.isEmpty()){
					 System.out.println("stack is not empty");
					 char operator = (char)s.pop();
					 postfix+=operator;
					 System.out.println("current postfix: " + postfix);
				 }
			 }
			//it's a number
			 else{
				 postfix += infix.charAt(i);
				 System.out.println("current postfix: " + postfix);
			 }			 
		 }
		 /*We have gone through the entire length of infix.
		  * Now, we need to fo through whatever's left in the stack
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

}
