package net.blogjava.frankiegao123.jpa.query;


public class TestEnum {

    public static void main(String[] args) {
        TestEnum te = new TestEnum();
        te.select(Season.FALL);
    }

    public void select(Season s) {
        s.print();
    }
}

enum Season {

    // 枚举是常量，最好使用常量的命名方法
    SPRING("春季"),
    SUMMER("夏季"),
    FALL("秋季"),
    WINTER("冬季")
    ;

    private String name;

    private Season(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name);
    }
}
