package io.github.zhdotm.ohzh.sieve.core.sql.visitor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.util.Collection;
import java.util.List;

/**
 * @author zhihao.mao
 */

public interface ISieveVisitor {

    /**
     * 给where添加add连接的条件
     *
     * @param plainSelect 查询
     * @param expression  条件表达式
     */
    default void addAndExpression4Where(PlainSelect plainSelect, Expression expression) {
        Expression where = plainSelect.getWhere();
        if (ObjectUtil.isEmpty(where)) {
            plainSelect.setWhere(expression);
        } else {
            plainSelect.setWhere(new AndExpression(where, expression));
        }
    }

    /**
     * 给join添加add连接的条件
     *
     * @param join       join
     * @param expression 条件表达式
     */
    default void addAndExpression4Join(Join join, Expression expression) {
        Collection<Expression> onExpressions = join.getOnExpressions();
        List<Expression> newOnExpressions = ListUtil.toList(onExpressions);
        if (CollectionUtil.isEmpty(newOnExpressions)) {
            newOnExpressions.add(expression);
        } else {
            net.sf.jsqlparser.expression.Expression onExpression = newOnExpressions.get(0);
            AndExpression newOnExpression = new AndExpression(onExpression, expression);
            newOnExpressions.remove(onExpression);
            newOnExpressions.add(0, newOnExpression);
        }
        join.setOnExpressions(newOnExpressions);
    }

}
