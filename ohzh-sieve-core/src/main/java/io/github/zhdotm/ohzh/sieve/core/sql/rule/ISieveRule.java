package io.github.zhdotm.ohzh.sieve.core.sql.rule;

import net.sf.jsqlparser.statement.Statement;

/**
 * 规则
 *
 * @author zhihao.mao
 */

public interface ISieveRule {

    /**
     * 是否可接受规则
     *
     * @param statement sql
     * @return 是/否
     */
    Boolean isAcceptable(Statement statement);

    /**
     * 接受规则
     *
     * @param statement sql
     */
    void accept(Statement statement);
}
