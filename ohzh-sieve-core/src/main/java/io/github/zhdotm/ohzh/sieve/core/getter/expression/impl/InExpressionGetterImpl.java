package io.github.zhdotm.ohzh.sieve.core.getter.expression.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.zhdotm.ohzh.sieve.core.getter.expression.IInExpressionGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

import java.util.List;

/**
 * @author zhihao.mao
 */

@Data
@AllArgsConstructor
public class InExpressionGetterImpl implements IInExpressionGetter {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 值
     */
    private List<String> values;

    @Override
    public InExpression getInExpression() {
        if (CollectionUtil.isEmpty(values)) {

            return null;
        }
        ExpressionList expressionList = new ExpressionList();
        values.forEach(value -> {
            expressionList.addExpressions(new StringValue(value));
        });

        return new InExpression(new Column(new Table(tableName), columnName), expressionList);
    }

}
