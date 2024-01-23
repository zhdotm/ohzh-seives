package io.github.zhdotm.ohzh.sieve.core.getter.expression.impl;

import cn.hutool.core.lang.Assert;
import io.github.zhdotm.ohzh.sieve.core.getter.expression.IExpressionGetter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhihao.mao
 */

public class EqualToExpressionGetterImplTest {

    private IExpressionGetter expressionGetter;

    @Before
    public void createEqualToExpressionGetter() {

        expressionGetter = new EqualToExpressionGetterImpl("table_1", "column_1", "xxx");
    }

    @Test
    public void test() {
        Assert.isTrue("table_1.column_1 = 'xxx'".equals(expressionGetter.getExpression().toString()));
    }

}