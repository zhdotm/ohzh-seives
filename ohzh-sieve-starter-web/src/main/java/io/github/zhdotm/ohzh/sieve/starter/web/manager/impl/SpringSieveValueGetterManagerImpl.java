package io.github.zhdotm.ohzh.sieve.starter.web.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.github.zhdotm.ohzh.sieve.core.getter.value.ISieveValueGetter;
import io.github.zhdotm.ohzh.sieve.core.manager.ISieveValueGetterManager;
import io.github.zhdotm.ohzh.sieve.core.manager.impl.CommonSieveValueGetterManagerImpl;
import io.github.zhdotm.ohzh.sieve.starter.web.getter.ISpringSieveValueGetter;
import io.github.zhdotm.ohzh.sieve.starter.web.manager.ISpringSieveValueGetterManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Map;

/**
 * @author zhihao.mao
 */

public class SpringSieveValueGetterManagerImpl implements ISpringSieveValueGetterManager, ApplicationRunner {

    private ISieveValueGetterManager valueGetterManager;

    @Override
    public void putValueGetter(ISieveValueGetter valueGetter) {
        valueGetterManager.putValueGetter(valueGetter);
        SpringUtil.registerBean(valueGetter.getName(), valueGetter);
    }

    @Override
    public ISieveValueGetter getValueGetter(String valueGetterName) {
        ISieveValueGetter valueGetter = valueGetterManager.getValueGetter(valueGetterName);
        if (ObjectUtil.isEmpty(valueGetter)) {
            valueGetter = SpringUtil.getBean(valueGetterName, ISieveValueGetter.class);
            if (ObjectUtil.isNotEmpty(valueGetter)) {
                valueGetterManager.putValueGetter(valueGetter);
            }
        }

        return valueGetter;
    }

    @Override
    public void removeValueGetter(String valueGetterName) {
        valueGetterManager.removeValueGetter(valueGetterName);
        SpringUtil.unregisterBean(valueGetterName);
    }

    @Override
    public void removeValueGetter(ISieveValueGetter valueGetter) {
        removeValueGetter(valueGetter.getName());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, ISpringSieveValueGetter> nameValueGetterMap = SpringUtil.getBeansOfType(ISpringSieveValueGetter.class);
        if (CollectionUtil.isEmpty(nameValueGetterMap)) {

            return;
        }
        if (ObjectUtil.isEmpty(valueGetterManager)) {
            valueGetterManager = CommonSieveValueGetterManagerImpl.getValueGetterManager();
        }
        nameValueGetterMap.values().forEach(valueGetterManager::putValueGetter);
    }
}
