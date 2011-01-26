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

    // ö���ǳ��������ʹ�ó�������������
    SPRING("����"),
    SUMMER("�ļ�"),
    FALL("�＾"),
    WINTER("����")
    ;

    private String name;

    private Season(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name);
    }
}
