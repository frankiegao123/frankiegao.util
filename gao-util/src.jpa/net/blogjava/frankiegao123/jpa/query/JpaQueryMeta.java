package net.blogjava.frankiegao123.jpa.query;

import java.beans.PropertyDescriptor;


import ognl.Ognl;
import ognl.OgnlException;

/**
 * <p>JPA ��ѯ�������Ԫ����</p>
 *
 * @author frankiegao123
 * 2010-6-18 ����11:28:35
 */
class JpaQueryMeta implements Comparable<JpaQueryMeta> {

    /**
     * ����������
     */
    private PropertyDescriptor propDesc;

    /**
     * ��ѯ�Ƚ�ģʽ
     */
    private QueryMode mode;

    /**
     * ��ѯ������Ե�������
     */
    private String queryProperty;

    /**
     * ��ѯ����ֵ�� OGNL ���ʽ
     */
    private String valueExpression;

    /**
     * ��ѯ����ֵ�� OGNL Node ����
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
