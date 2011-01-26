package net.blogjava.frankiegao123.jpa.query.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>JPA 查询条件集标注</p>
 * @author frankiegao123
 * 2010-6-21 上午10:25:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JpaQueries {

    /**
     * <p>JPA 查询条件标注</p>
     * @return
     * @author frankiegao123
     * 2010-6-21 上午10:25:22
     */
    JpaQuery[] value();
}