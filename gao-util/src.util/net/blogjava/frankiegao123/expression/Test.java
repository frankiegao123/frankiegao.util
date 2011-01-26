package net.blogjava.frankiegao123.expression;

public class Test {

    public static void main(String[] args) {
        String str = "((hello @ l)&(a world @ a))";
        Calculator c = Calculator.getInstance();
        System.out.println(c.eval(str));
    }
}
