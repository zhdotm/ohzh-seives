package io.github.zhdotm.ohzh.sieve.core.sql.visitor;

import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.statement.select.PivotVisitor;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;

/**
 * @author zhihao.mao
 */

public interface ISieveExpressionVisitor extends ExpressionVisitor, ItemsListVisitor, PivotVisitor, SelectItemVisitor, ISieveVisitor {
}
