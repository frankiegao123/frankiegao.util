package net.blogjava.frankiegao123.jpa.query.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.blogjava.frankiegao123.jpa.query.QueryMode;

/**
 * <p>��ѯ������ע</p>
 * @author frankiegao123
 * 2010-6-21 ����10:25:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface JpaQuery {

    /**
     * ��ѯģʽ�����ڡ����ڵȣ�
     * @return
     * @author frankiegao123
     * 2010-6-21 ����10:25:51
     */
    QueryMode mode() default QueryMode.EQ;

    /**
     * ��ѯ������Ӧ�� JPA Entity ������
     * @return
     * @author frankiegao123
     * 2010-6-21 ����10:26:09
     */
    String property() default "";

    /**
     * ��ע���Զ��������ʱ������������������
     * @return
     * @author frankiegao123
     * 2010-6-21 ����10:26:22
     */
    String objectProperty() default "";

    /**
     * ��Ҫ������ѯ�������Ⱥ�˳����ֵԽСλ��Խ��ǰ
     * @return
     * @author frankiegao123
     * 2010-6-21 ����10:28:18
     */
    int index() default 0;
}