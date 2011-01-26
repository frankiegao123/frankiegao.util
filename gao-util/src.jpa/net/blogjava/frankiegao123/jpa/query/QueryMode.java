package net.blogjava.frankiegao123.jpa.query;

public enum QueryMode {

    /**
     * 等于（=）
     */
    EQ("="),

    /**
     * 不等于（&lt;&gt;）
     */
    NE("<>"),

    /**
     * 大于（&gt;）
     */
    GT(">"),

    /**
     * 大于等于（&gt;=）
     */
    GE(">="),

    /**
     * 小于（&lt;）
     */
    LT("<"),

    /**
     * 小于等于（&lt;=）
     */
    LE("<="),

    /**
     * 全 LIKE（LIKE %xxx%）
     */
    LIKE("LIKE") {
        Object buildQueryValue(Object value, JpaQueryMeta meta) {
            return escapeLike(value, true, true, meta);
        }
    },

    /**
     * 右 LIKE（LIKE xxx%）
     */
    LIKE_RIGHT("LIKE") {
        Object buildQueryValue(Object value, JpaQueryMeta meta) {
            return escapeLike(value, false, true, meta);
        }
    },

    /**
     * 左 LIKE（LIKE %xxx）
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
     * <p>转义 LIKE 查询字符串中的特殊字符，比如“_”和“%”，并添加用于 LIKE 查询的 %</p>
     *
     * @param obj       查询条件值
     * @param head      是否需要左百分号
     * @param tail      是否需要右百分号
     * @param meta      查询标注元数据
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