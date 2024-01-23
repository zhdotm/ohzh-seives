package io.github.zhdotm.ohzh.sieve.core.getter.expression;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;

/**
 * @author zhihao.mao
 */

public interface IEqualToExpressionGetter extends IExpressionGetter {

    /**
     * 获取=表达式
     *
     * @return =表达式
     */
    EqualsTo getEqualExpression();

    @Override
    default Expression getExpression() {

        return getEqualExpression();
    }

}
