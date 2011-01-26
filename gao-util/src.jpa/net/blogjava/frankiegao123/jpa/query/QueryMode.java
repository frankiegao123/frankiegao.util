package net.blogjava.frankiegao123.jpa.query;

public enum QueryMode {

    /**
     * ���ڣ�=��
     */
    EQ("="),

    /**
     * �����ڣ�&lt;&gt;��
     */
    NE("<>"),

    /**
     * ���ڣ�&gt;��
     */
    GT(">"),

    /**
     * ���ڵ��ڣ�&gt;=��
     */
    GE(">="),

    /**
     * С�ڣ�&lt;��
     */
    LT("<"),

    /**
     * С�ڵ��ڣ�&lt;=��
     */
    LE("<="),

    /**
     * ȫ LIKE��LIKE %xxx%��
     */
    LIKE("LIKE") {
        Object buildQueryValue(Object value, JpaQueryMeta meta) {
            return escapeLike(value, true, true, meta);
        }
    },

    /**
     * �� LIKE��LIKE xxx%��
     */
    LIKE_RIGHT("LIKE") {
        Object buildQueryValue(Object value, JpaQueryMeta meta) {
            return escapeLike(value, false, true, meta);
        }
    },

    /**
     * �� LIKE��LIKE %xxx��
     */
    LIKE_LEFT("LIKE") {
        Object buildQueryValue(Object value, JpaQueryMeta meta) {
            return escapeLike(value, true, false, meta);
        }
    }
    ;

    private String operator;
    private boolean like;

    QueryMode(String operator) {
        this.operator = operator;
        this.like = "LIKE".equals(operator);
    }

    Object buildQueryValue(Object value, JpaQueryMeta meta) {
        return value;
    }

    boolean isLike() {
        return like;
    }

    StringBuilder buildQuery(StringBuilder sb, int index, JpaQueryMeta meta) {
        sb.append(' ').append(JpaQueryBuilder.ENTITY_ALIAS).append('.').append(meta.getQueryProperty());
        sb.append(' ').append(operator).append(' ');
        sb.append('?').append(index);
        return sb;
    }

    /**
     * <p>ת�� LIKE ��ѯ�ַ����е������ַ������硰_���͡�%������������� LIKE ��ѯ�� %</p>
     *
     * @param obj       ��ѯ����ֵ
     * @param head      �Ƿ���Ҫ��ٷֺ�
     * @param tail      �Ƿ���Ҫ�Ұٷֺ�
     * @param meta      ��ѯ��עԪ����
     * @return
     */
    private static String escapeLike(Object obj, boolean head, boolean tail, JpaQueryMeta meta) {
        if(!(obj instanceof String)) {
            throw new IllegalStateException("like query must be String type, Entity property name: " + meta.getQueryProperty() + ", value: " + obj);
        }
        StringBuilder sb = new StringBuilder();
        char[] chs = ((String)obj).toCharArray();
        if(head) {
            sb.append('%');
        }
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] == '_' || chs[i] == '%') {
                sb.append(JpaQueryBuilder.ESCAPE_CHAR);
            }
            sb.append(chs[i]);
        }
        if(tail) {
            sb.append('%');
        }
        return sb.toString();
    }
}