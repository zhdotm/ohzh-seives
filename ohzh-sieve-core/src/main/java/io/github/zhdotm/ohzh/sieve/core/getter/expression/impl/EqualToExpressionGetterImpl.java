package io.github.zhdotm.ohzh.sieve.core.getter.expression.impl;

import io.github.zhdotm.ohzh.sieve.core.getter.expression.IEqualToExpressionGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

/**
 * @author zhihao.mao
 */

@Data
@AllArgsConstructor
public class EqualToExpressionGetterImpl implements IEqualToExpressionGetter {

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
    private String value;

    @Override
    public EqualsTo getEqualExpression() {

        return new EqualsTo(new Column(new Table(tableName), columnName), new StringValue(value));
    }

}
