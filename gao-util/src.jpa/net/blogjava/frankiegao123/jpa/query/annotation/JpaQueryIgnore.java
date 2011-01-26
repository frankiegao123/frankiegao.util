package net.blogjava.frankiegao123.jpa.query.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>JPA 查询忽略该属性。即不扫描该属性数据。</p>
 * @author frankiegao123
 * 2010-6-21 上午10:28:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JpaQueryIgnore {
}
