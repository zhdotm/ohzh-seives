package io.github.zhdotm.ohzh.sieve.core.annotation;

import java.lang.annotation.*;

/**
 * 筛子
 *
 * @author zhihao.mao
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Sieves {

    /**
     * 值筛子
     *
     * @return 筛子
     */
    ValueSieve[] valueSieves() default {};

    /**
     * 表达式筛子
     *
     * @return 筛子
     */
    ExpressionSieve[] expressionSieves() default {};
}
