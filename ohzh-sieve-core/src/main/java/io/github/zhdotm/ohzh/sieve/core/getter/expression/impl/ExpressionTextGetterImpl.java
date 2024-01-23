package io.github.zhdotm.ohzh.sieve.core.getter.expression.impl;

import io.github.zhdotm.ohzh.sieve.core.getter.expression.IExpressionTextGetter;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhihao.mao
 */

@Data
@AllArgsConstructor
public class ExpressionTextGetterImpl implements IExpressionTextGetter {

    /**
     * 表达式文本
     */
    private final String expressionText;

}
