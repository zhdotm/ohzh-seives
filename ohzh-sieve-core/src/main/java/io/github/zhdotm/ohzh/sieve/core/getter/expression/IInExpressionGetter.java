package io.github.zhdotm.ohzh.sieve.core.getter.expression;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.InExpression;

/**
 * @author zhihao.mao
 */

public interface IInExpressionGetter extends IExpressionGetter {

    /**
     * 获取in表达式
     *
     * @return in表达式
     */
    InExpression getInExpression();

    @Override
    default Expression getExpression() {

        return getInExpression();
    }

}
