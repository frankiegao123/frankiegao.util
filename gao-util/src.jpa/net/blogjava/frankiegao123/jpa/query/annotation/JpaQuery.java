package net.blogjava.frankiegao123.jpa.query.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.blogjava.frankiegao123.jpa.query.QueryMode;

/**
 * <p>查询条件标注</p>
 * @author frankiegao123
 * 2010-6-21 上午10:25:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface JpaQuery {

    /**
     * 查询模式（等于、大于等）
     * @return
     * @author frankiegao123
     * 2010-6-21 上午10:25:51
     */
    QueryMode mode() default QueryMode.EQ;

    /**
     * 查询条件对应的 JPA Entity 属性名
     * @return
     * @author frankiegao123
     * 2010-6-21 上午10:26:09
     */
    String property() default "";

    /**
     * 标注于自定义对象上时，设置条件的属性名
     * @return
     * @author frankiegao123
     * 2010-6-21 上午10:26:22
     */
    String objectProperty() default "";

    /**
     * 需要产生查询条件的先后顺序，数值越小位置越靠前
     * @return
     * @author frankiegao123
     * 2010-6-21 上午10:28:18
     */
    int index() default 0;
}