package net.blogjava.frankiegao123.jpa.query;

import java.beans.PropertyDescriptor;


import ognl.Ognl;
import ognl.OgnlException;

/**
 * <p>JPA 查询语句属性元数据</p>
 *
 * @author frankiegao123
 * 2010-6-18 下午11:28:35
 */
class JpaQueryMeta implements Comparable<JpaQueryMeta> {

    /**
     * 属性描述符
     */
    private PropertyDescriptor propDesc;

    /**
     * 查询比较模式
     */
    private QueryMode mode;

    /**
     * 查询条件针对的属性名
     */
    private String queryProperty;

    /**
     * 查询条件值的 OGNL 表达式
     */
    private String valueExpression;

    /**
     * 查询条件值的 OGNL Node 对象
     */
    private Object expressionNode;

    private int index;

    JpaQueryMeta(PropertyDescriptor propDesc, QueryMode mode, String queryProperty,
            int index) throws Exception {
        this(propDesc, mode, queryProperty, queryProperty, index);
    }

    JpaQueryMeta(PropertyDescriptor propDesc, QueryMode mode, String queryProperty,
            String valueExpression, int index) throws Exception {
        this.propDesc = propDesc;
        this.mode = mode;
        this.queryProperty = queryProperty;
        this.valueExpression = valueExpression;
        this.expressionNode = parseExpression(valueExpression);
        this.index = index;
    }

    String getQueryProperty() {
        return queryProperty;
    }

    private static Object parseExpression(String expression) throws Exception {
        return Ognl.parseExpression(expression);
    }

    Object getValue(Object context) throws OgnlException {
        return Ognl.getValue(expressionNode, context);
    }

    StringBuilder buildQuery(StringBuilder sb, int index) {
        return mode.buildQuery(sb, index, this);
    }

    Object buildQueryValue(Object value) {
        return mode.buildQueryValue(value, this);
    }

    boolean isLike() {
        return mode.isLike();
    }

    public String toString() {
        return "[JpaQueryMeta] propertyDescriptor=" + propDesc.getName() +
            ", queryMode=" + mode + ", queryProperty=" + queryProperty +
            ", valueExpression=" + valueExpression;
    }

    public int compareTo(JpaQueryMeta o) {
        return index - o.index;
    }
}
