package net.blogjava.frankiegao123.expression;

import java.util.HashMap;
import java.util.Map;

/**
 * 运算符
 */
public abstract class Operator {

    /**
     * 运算符
      */
    private char operator;

    /**
     * 运算符的优先级别，数字越大，优先级别越高
      */
    private int priority;

    private static Map<Character, Operator> operators = new HashMap<Character, Operator>();

    private Operator(char operator, int priority) {
        setOperator(operator);
        setPriority(priority);
        register(this);
    }

    private void register(Operator operator) {
        operators.put(operator.getOperator(), operator);
    }

    /**
     * 截取运算
      */
    public final static Operator ADITION = new Operator('@', 100) {
            public String eval(String left, String right) {
                int index = left.indexOf(right);
                if(index < 0) {
                    return "";
                }
                return left.substring(index + right.length());
            }
        };

    /**
     * 连接运算
      */
    public final static Operator SUBTRATION = new Operator('&', 200) {
            public String eval(String left, String right) {
                return left + right;
            }
        };

    public char getOperator() {
        return operator;
    }
    private void setOperator(char operator) {
        this.operator = operator;
    }
    public int getPriority() {
        return priority;
    }
    private void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * 根据某个运算符获得该运算符的优先级别
      * @param c
     * @return      运算符的优先级别
      */
    public static int getPrority(char c) {
        Operator op = operators.get(c);
        if(op != null) {
            return op.getPriority();
        }
        return 0;
    }

    /**
     * 工具方法，判断某个字符是否是运算符
      * @param c
     * @return      是运算符返回 true，否则返回 false
     */
    public static boolean isOperator(char c) {
        return getInstance(c) != null;
    }
    public static boolean isOperator(String str) {
        if(str.length() > 1) {
            return false;
        }
        return isOperator(str.charAt(0));
    }

    /**
     * 根据运算符获得 Operator 实例
      * @param c
     * @return      从注册中的 Operator 返回实例，尚未注册返回 null
     */
    public static Operator getInstance(char c) {
        return operators.get(c);
    }
    public static Operator getInstance(String str) {
        if(str.length() > 1) {
            return null;
        }
        return getInstance(str.charAt(0));
    }

    /**
     * 根据操作数进行计算
      * @param left    左操作数
      * @param right   右操作数
      * @return        计算结果
      */
    public abstract String eval(String left, String right);
}