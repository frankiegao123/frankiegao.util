package net.blogjava.frankiegao123.jpa.query.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>JPA ��ѯ��������ע</p>
 * @author frankiegao123
 * 2010-6-21 ����10:25:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JpaQueries {

    /**
     * <p>JPA ��ѯ������ע</p>
     * @return
     * @author frankiegao123
     * 2010-6-21 ����10:25:22
     */
    JpaQuery[] value();
}