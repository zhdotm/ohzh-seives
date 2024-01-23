package io.github.zhdotm.ohzh.sieve.core.sql.rule;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

/**
 * 查询规则
 *
 * @author zhihao.mao
 */

public interface ISieveSelectRule extends ISieveRule {

    @Override
    default Boolean isAcceptable(Statement statement) {

        return statement instanceof Select;
    }

    /**
     * 接受查询规则
     *
     * @param select 查询sql
     */
    void acceptSelect(Select select);

    @Override
    default void accept(Statement statement) {
        if (!isAcceptable(statement)) {

            return;
        }

        acceptSelect((Select) statement);
    }

}
