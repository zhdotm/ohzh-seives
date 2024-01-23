package io.github.zhdotm.ohzh.sieve.core.sql.visitor.Impl;

import io.github.zhdotm.ohzh.sieve.core.sql.visitor.ISieveExpressionVisitor;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.statement.select.SelectVisitor;

/**
 * @author zhihao.mao
 */

public class SieveExpressionVisitorImpl extends ExpressionVisitorAdapter implements ISieveExpressionVisitor {

    public SieveExpressionVisitorImpl(SelectVisitor selectVisitor) {
        setSelectVisitor(selectVisitor);
    }

}
