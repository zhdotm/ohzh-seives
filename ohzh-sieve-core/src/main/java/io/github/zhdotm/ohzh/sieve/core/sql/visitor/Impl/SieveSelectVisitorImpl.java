package io.github.zhdotm.ohzh.sieve.core.sql.visitor.Impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.sieve.core.sql.visitor.ISieveSelectVisitor;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectVisitorAdapter;

import java.util.Optional;

/**
 * @author zhihao.mao
 */

@AllArgsConstructor
public class SieveSelectVisitorImpl extends SelectVisitorAdapter implements ISieveSelectVisitor {

    private final String acceptTableName;

    private final Expression acceptExpression;

    @SneakyThrows
    @Override
    public void visit(PlainSelect plainSelect) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table table = (Table) fromItem;
            String tableName = table.getName();
            if (StrUtil.equalsIgnoreCase(acceptTableName, tableName)) {
                addAndExpression4Where(plainSelect, acceptExpression);
            }
        }

        SieveFromItemVisitorImpl sieveFromItemVisitor = new SieveFromItemVisitorImpl(acceptTableName, acceptExpression);
        fromItem.accept(sieveFromItemVisitor);

        for (Join join : Optional.ofNullable(plainSelect.getJoins()).orElse(ListUtil.list(Boolean.FALSE))) {
            FromItem rightItem = join.getRightItem();
            if (rightItem instanceof Table) {
                String tableName = ((Table) rightItem).getName();
                if (StrUtil.equalsIgnoreCase(acceptTableName, tableName)) {
                    addAndExpression4Join(join, acceptExpression);
                }
            }
            rightItem.accept(sieveFromItemVisitor);
        }

        Expression where = plainSelect.getWhere();
        if (ObjectUtil.isNotEmpty(where)) {
            where.accept(new SieveExpressionVisitorImpl(this));
        }
    }

}
