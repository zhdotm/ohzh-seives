package io.github.zhdotm.ohzh.sieve.core.getter.expression.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Assert;
import io.github.zhdotm.ohzh.sieve.core.getter.expression.IExpressionGetter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhihao.mao
 */

public class InExpressionGetterImplTest {

    private IExpressionGetter expressionGetter;

    @Before
    public void createInExpressionGetter() {
        expressionGetter = new InExpressionGetterImpl("table_1", "column_1", ListUtil.of("aaa", "bbb", "ccc"));
    }

    @Test
    public void test() {
        Assert.isTrue("table_1.column_1 IN ('aaa', 'bbb', 'ccc')".equals(expressionGetter.getExpression().toString()));
    }

}