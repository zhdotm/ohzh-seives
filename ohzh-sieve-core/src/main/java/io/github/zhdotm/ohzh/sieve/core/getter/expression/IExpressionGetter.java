package io.github.zhdotm.ohzh.sieve.core.getter.expression;

import net.sf.jsqlparser.expression.Expression;

/**
 * @author zhihao.mao
 */

public interface IExpressionGetter {

    /**
     * 获取表达式
     *
     * @return 表达式
     */
    Expression getExpression();

}
