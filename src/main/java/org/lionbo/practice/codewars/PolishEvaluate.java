package org.lionbo.practice.codewars;

import java.util.Stack;

public class PolishEvaluate {
    public double evaluate(String expr) {

        if (expr == null || expr.equals("")) {
            return 0;
        }

        String[] strs = expr.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strs.length; i++) {
            String first = "";
            String second = "";
            String s = "";
            switch (strs[i]) {
            case "+":
                first = stack.pop();
                second = stack.pop();
                s = Double.valueOf(second) + Double.valueOf(first) + "";
                stack.push(s);
                break;
            case "-":
                first = stack.pop();
                second = stack.pop();
                s = Double.valueOf(second) - Double.valueOf(first) + "";
                stack.push(s);
                break;
            case "/":
                first = stack.pop();
                second = stack.pop();
                s = Double.valueOf(second) / Double.valueOf(first) + "";
                stack.push(s);
                break;
            case "*":
                first = stack.pop();
                second = stack.pop();
                s = Double.valueOf(second) * Double.valueOf(first) + "";
                stack.push(s);
                break;
            default:
                stack.push(strs[i]);
            }
        }
        String result = stack.pop();

        return Double.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(new PolishEvaluate().evaluate("5 1 2 + 4 * + 3 -"));
    }
}
