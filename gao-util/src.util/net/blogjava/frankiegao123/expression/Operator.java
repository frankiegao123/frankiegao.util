package net.blogjava.frankiegao123.expression;

import java.util.HashMap;
import java.util.Map;

/**
 * �����
 */
public abstract class Operator {

    /**
     * �����
      */
    private char operator;

    /**
     * ����������ȼ�������Խ�����ȼ���Խ��
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
     * ��ȡ����
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
     * ��������
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
     * ����ĳ���������ø�����������ȼ���
      * @param c
     * @return      ����������ȼ���
      */
    public static int getPrority(char c) {
        Operator op = operators.get(c);
        if(op != null) {
            return op.getPriority();
        }
        return 0;
    }

    /**
     * ���߷������ж�ĳ���ַ��Ƿ��������
      * @param c
     * @return      ����������� true�����򷵻� false
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
     * ������������ Operator ʵ��
      * @param c
     * @return      ��ע���е� Operator ����ʵ������δע�᷵�� null
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
     * ���ݲ��������м���
      * @param left    �������
      * @param right   �Ҳ�����
      * @return        ������
      */
    public abstract String eval(String left, String right);
}