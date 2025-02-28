//Chanchakorn Jullapech
//672115007
import java.io.*;
import java.util.Scanner;

public class InfixToPostfix {
    public static String Convert(String infix) {
        Stack stack = new Stack();
        StringBuilder postfix = new StringBuilder();
        
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            
            if (c == ' ') continue;
            
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push("(");
            } else if (c == ')') {
                while (!stack.isEmpty() && !stack.peek().data.equals("(")) {
                    postfix.append(stack.pop().data);
                }
                if (!stack.isEmpty()) stack.pop(); 
            } else {
             
                while (!stack.isEmpty() && !stack.peek().data.equals("(") && 
                       precedence(c) <= precedence(stack.peek().data.charAt(0))) {
                    postfix.append(stack.pop().data);
                }
                stack.push(String.valueOf(c));
            }
        }
        
        
        while (!stack.isEmpty()) {
            if (stack.peek().data.equals("(")) {
                return "Invalid Expression"; 
            }
            postfix.append(stack.pop().data);
        }
        
        return postfix.toString();
    }
    
    public static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
    
    public static boolean isValidInfix(String infix) {
        if (infix.isEmpty()) return false;
        
    
        infix = infix.replaceAll("\\s+", "");
        
        int open = 0, close = 0;
        boolean lastWasOperator = true; 
        boolean lastWasOperand = false;
        
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            
            if (Character.isLetterOrDigit(c)) {
                lastWasOperand = true;
                lastWasOperator = false;
            } else if (c == '(') {
                open++;
                lastWasOperand = false;
                lastWasOperator = true;
            } else if (c == ')') {
                close++;
                if (lastWasOperator && !lastWasOperand) return false; 
                if (close > open) return false; 
                lastWasOperand = true;
                lastWasOperator = false;
            } else if ("+-*/^".indexOf(c) != -1) {
                if (lastWasOperator) return false;
                lastWasOperand = false;
                lastWasOperator = true;
            } else {
                return false; 
            }
        }
        
        return open == close && lastWasOperand; 
    }
    
    public static void main(String[] args) throws Exception {
        File file = new File("input1.csv");
        Scanner sc = new Scanner(file);
        String infix;
        
        while (sc.hasNextLine()) {
            infix = sc.nextLine().trim();
            if (isValidInfix(infix)) {
                System.out.println("Infix: " + infix);
                System.out.println("Valid");
                System.out.println("Postfix: " + Convert(infix));
            } else {
                System.out.println("Infix: " + infix);
                System.out.println("Not valid");
            }
        }
    
    }
}
