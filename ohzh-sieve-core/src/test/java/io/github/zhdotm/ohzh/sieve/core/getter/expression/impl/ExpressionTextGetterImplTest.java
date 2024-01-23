package io.github.zhdotm.ohzh.sieve.core.getter.expression.impl;

import cn.hutool.core.lang.Assert;
import io.github.zhdotm.ohzh.sieve.core.getter.expression.IExpressionGetter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhihao.mao
 */

public class ExpressionTextGetterImplTest {

    private IExpressionGetter expressionGetter;

    @Before
    public void createExpressionTextGetter() {
        expressionGetter = new ExpressionTextGetterImpl("table_1.column_1 = 'xxx'");
    }

    @Test
    public void test() {
        Assert.isTrue("table_1.column_1 = 'xxx'".equals(expressionGetter.getExpression().toString()));
    }

}