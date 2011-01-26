package net.blogjava.frankiegao123.expression;

public interface Symbol {

    /**
     * 左括号
      */
    public final static char LEFT_BRACKET   = '(';

    /**
     * 右括号
      */
    public final static char RIGHT_BRACKET  = ')';

    /**
     * 中缀表达式中的空格，需要要忽略
      */
    public final static char BLANK          = ' ';

    /**
     * 后缀表达式的各段的分隔符
      */
    public final static char SEPARATOR      = '|';
}
