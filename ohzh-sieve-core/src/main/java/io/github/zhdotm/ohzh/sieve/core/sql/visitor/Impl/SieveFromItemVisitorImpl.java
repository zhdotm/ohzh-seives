package io.github.zhdotm.ohzh.sieve.core.sql.visitor.Impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.sieve.core.sql.visitor.ISieveFromItemVisitor;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;

import java.util.List;

/**
 * @author zhihao.mao
 */

@AllArgsConstructor
public class SieveFromItemVisitorImpl extends FromItemVisitorAdapter implements ISieveFromItemVisitor {

    private final String acceptTableName;

    private final Expression acceptExpression;

    @Override
    public void visit(SubSelect subSelect) {
        SelectBody selectBody = subSelect.getSelectBody();
        SieveSelectVisitorImpl sieveSelectVisitor = new SieveSelectVisitorImpl(acceptTableName, acceptExpression);
        selectBody.accept(sieveSelectVisitor);
    }

    @Override
    public void visit(SubJoin subjoin) {
        FromItem leftFromItem = subjoin.getLeft();
        boolean isAcceptable = Boolean.FALSE;
        if (leftFromItem instanceof Table) {
            String tableName = ((Table) leftFromItem).getName();
            isAcceptable = StrUtil.equalsIgnoreCase(acceptTableName, tableName);
        }
        leftFromItem.accept(this);
        List<Join> joins = subjoin.getJoinList();
        if (CollectionUtil.isEmpty(joins)) {

            return;
        }

        for (Join join : joins) {
            FromItem rightItem = join.getRightItem();
            if (!isAcceptable && (rightItem instanceof Table)) {
                String tableName = ((Table) rightItem).getName();
                isAcceptable = StrUtil.equalsIgnoreCase(acceptTableName, tableName);
            }
            if (isAcceptable) {
                addAndExpression4Join(join, acceptExpression);
            }
            rightItem.accept(this);
        }
    }

}
