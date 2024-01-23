package io.github.zhdotm.ohzh.sieve.core.getter.value;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.zhdotm.ohzh.sieve.core.getter.expression.IExpressionTextGetter;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;

import java.util.List;

/**
 * @author zhihao.mao
 */

public interface IExpressionTextSieveValueGetter extends IExpressionTextGetter, ISieveValueGetter {

    @SneakyThrows
    @Override
    default String getExpressionText() {
        List<String> expressionTextValues = get();
        if (CollectionUtil.isEmpty(expressionTextValues)) {

            return CCJSqlParserUtil.parse(" 1 != 1 ").toString();
        }

        Expression expression = null;
        for (String expressionTextValue : expressionTextValues) {
            if (ObjectUtil.isEmpty(expression)) {
                expression = CCJSqlParserUtil.parseExpression(expressionTextValue);
                continue;
            }
            expression = new AndExpression(expression, CCJSqlParserUtil.parseExpression(expressionTextValue));
        }

        if (ObjectUtil.isEmpty(expression)) {

            return null;
        }

        return expression.toString();
    }

}
