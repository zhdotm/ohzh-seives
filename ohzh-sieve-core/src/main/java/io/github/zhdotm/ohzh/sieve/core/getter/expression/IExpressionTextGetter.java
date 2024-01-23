package io.github.zhdotm.ohzh.sieve.core.getter.expression;

import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;

/**
 * @author zhihao.mao
 */

public interface IExpressionTextGetter extends IExpressionGetter {

    /**
     * 获取表达式文本
     *
     * @return 表达式文本
     */
    String getExpressionText();

    @SneakyThrows
    @Override
    default Expression getExpression() {

        return CCJSqlParserUtil.parseExpression(getExpressionText());
    }

}
