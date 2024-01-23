package io.github.zhdotm.ohzh.sieve.core.sql.rule.impl;

import io.github.zhdotm.ohzh.sieve.core.sql.rule.ISieveSelectRule;
import io.github.zhdotm.ohzh.sieve.core.sql.visitor.ISieveSelectVisitor;
import io.github.zhdotm.ohzh.sieve.core.sql.visitor.Impl.SieveSelectVisitorImpl;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;

/**
 * @author zhihao.mao
 */

@AllArgsConstructor
public class SieveSelectRuleImpl implements ISieveSelectRule {

    private final String acceptTableName;

    private final Expression acceptExpression;

    @Override
    public void acceptSelect(Select select) {
        ISieveSelectVisitor sieveSelectVisitor = new SieveSelectVisitorImpl(acceptTableName, acceptExpression);
        SelectBody selectBody = select.getSelectBody();
        selectBody.accept(sieveSelectVisitor);
    }

}
