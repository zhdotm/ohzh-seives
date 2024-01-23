package io.github.zhdotm.ohzh.sieve.core.getter.expression.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.github.zhdotm.ohzh.sieve.core.getter.value.ISieveValueGetter;
import io.github.zhdotm.ohzh.sieve.core.manager.ISieveValueGetterManager;
import io.github.zhdotm.ohzh.sieve.core.manager.impl.CommonSieveValueGetterManagerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhihao.mao
 */

public class CommonValueGetterManagerImplTest {

    private ISieveValueGetterManager valueGetterManager;

    @Before
    public void createCommonValueGetterManager() {
        valueGetterManager = CommonSieveValueGetterManagerImpl.getValueGetterManager();
    }

    @Test
    public void putValueGetter() {
        valueGetterManager.putValueGetter(new ISieveValueGetter() {
            @Override
            public String getName() {

                return "valueGetter_1";
            }

            @Override
            public List<String> get() {
                return ListUtil.of("1", "2", "3");
            }
        });

        valueGetterManager.putValueGetter(new ISieveValueGetter() {
            @Override
            public String getName() {

                return "valueGetter_2";
            }

            @Override
            public List<String> get() {
                return ListUtil.of("a", "b", "c");
            }
        });

        valueGetterManager.putValueGetter(new ISieveValueGetter() {
            @Override
            public String getName() {

                return "valueGetter_3";
            }

            @Override
            public List<String> get() {
                return ListUtil.of("x", "y", "z");
            }
        });
    }

    @Test
    public void getValueGetter() {
        putValueGetter();
        Assert.isTrue("[x, y, z]".equals(Arrays.toString(valueGetterManager.getValueGetter("valueGetter_3").get().toArray())));
    }

    @Test
    public void removeValueGetter() {
        putValueGetter();
        Assert.isTrue("[x, y, z]".equals(Arrays.toString(valueGetterManager.getValueGetter("valueGetter_3").get().toArray())));
        valueGetterManager.removeValueGetter("valueGetter_3");
        Assert.isTrue(ObjectUtil.isEmpty(valueGetterManager.getValueGetter("valueGetter_3")));
    }

}