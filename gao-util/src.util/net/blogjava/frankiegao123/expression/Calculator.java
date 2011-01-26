package net.blogjava.frankiegao123.expression;

import java.util.Stack;
import java.util.regex.Pattern;

public class Calculator implements Symbol {

    private final static Calculator instance = new Calculator();

    private Calculator() {
    }

    public static Calculator getInstance() {
        return instance;
    }

    /**
     * ������׺���ʽ
      * @param expression
     * @return
     */
    public String eval(String expression) {
        String str = infix2Suffix(expression);
//        System.out.println(" Infix Expression: " + expression);
//        System.out.println("Suffix Expression: " + str);
        if(str == null) {
            throw new IllegalArgumentException("Expression is null!");
        }
        String[] strs = str.split(Pattern.quote(String.valueOf(SEPARATOR)));
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < strs.length; i++) {
            strs[i] = strs[i].trim();
            if(!Operator.isOperator(strs[i])) {
                stack.push(strs[i]);
            } else {
                Operator op = Operator.getInstance(strs[i]);
                String right = stack.pop();
                String left = stack.pop();
                stack.push( op.eval(left, right) );
            }
        }
        return stack.pop();
    }

    /**
     * ����׺���ʽת��Ϊ��׺���ʽ
      * @param expression
     * @return
     */
    public String infix2Suffix(String expression) {
        if(expression == null) {
            return null;
        }
        char[] chs = expression.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb = new StringBuilder(chs.length);
        boolean appendSeparator = false;
        for(int i = 0; i < chs.length; i++) {
            char c = chs[i];
            if(appendSeparator) {
                sb.append(SEPARATOR);
                appendSeparator = false;
            }
            if(isLeftBracket(c)) {
                stack.push(c);
                continue;
            }
            if(isRightBracket(c)) {
                while(stack.peek() != LEFT_BRACKET) {
                    sb.append(SEPARATOR);
                    sb.append(stack.pop());
                }
                stack.pop();
                continue;
            }
            if(!Operator.isOperator(c)) {
                sb.append(c);
                continue;
            }
            appendSeparator = true;
            if(Operator.isOperator(c)) {
                if(stack.isEmpty() || stack.peek() == LEFT_BRACKET) {
                    stack.push(c);
                    continue;
                }
                int precedence = Operator.getPrority(c);
                while(!stack.isEmpty() && Operator.getPrority(stack.peek()) >= precedence) {
                    sb.append(SEPARATOR);
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while(!stack.isEmpty()) {
            sb.append(SEPARATOR);
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * �ж�ĳ���ַ��Ƿ�Ϊ������
      * @param c
     * @return
     */
    private boolean isLeftBracket(char c) {
        return c == LEFT_BRACKET;
    }

    /**
     * �ж�ĳ���ַ��Ƿ�Ϊ������
      * @param c
     * @return
     */
    private boolean isRightBracket(char c) {
        return c == RIGHT_BRACKET;
    }
}
