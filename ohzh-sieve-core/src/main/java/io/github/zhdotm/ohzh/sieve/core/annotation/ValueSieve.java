package io.github.zhdotm.ohzh.sieve.core.annotation;

import java.lang.annotation.*;

/**
 * 值筛子
 *
 * @author zhihao.mao
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ValueSieve {

    /**
     * 指定表
     *
     * @return 表
     */
    String tableName();

    /**
     * 指定字段
     *
     * @return 字段
     */
    String columnName();

    /**
     * 值获取器类名称
     *
     * @return 获取器类
     */
    String valueGetterName();

    /**
     * 用途描述（没有实际作用，只是为了方便梳理逻辑）
     *
     * @return 用途描述
     */
    String description() default "";

}
