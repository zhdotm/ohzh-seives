package io.github.zhdotm.ohzh.sieve.core.manager.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.sieve.core.getter.value.ISieveValueGetter;
import io.github.zhdotm.ohzh.sieve.core.manager.ISieveValueGetterManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author zhihao.mao
 */

public class CommonSieveValueGetterManagerImpl implements ISieveValueGetterManager {

    private static final Map<String, ISieveValueGetter> VALUE_GETTER_MAP = new HashMap<>();

    private static volatile CommonSieveValueGetterManagerImpl commonValueGetterManager;

    private CommonSieveValueGetterManagerImpl() {

    }

    public synchronized static ISieveValueGetterManager getValueGetterManager() {
        commonValueGetterManager = Optional.ofNullable(commonValueGetterManager)
                .orElse(new CommonSieveValueGetterManagerImpl());

        return commonValueGetterManager;
    }

    @Override
    public synchronized void putValueGetter(ISieveValueGetter valueGetter) {
        if (ObjectUtil.isEmpty(valueGetter)) {

            return;
        }

        VALUE_GETTER_MAP.put(valueGetter.getName(), valueGetter);
    }

    @Override
    public ISieveValueGetter getValueGetter(String valueGetterName) {

        return VALUE_GETTER_MAP.get(valueGetterName);
    }

    @Override
    public synchronized void removeValueGetter(String valueGetterName) {
        if (StrUtil.isEmpty(valueGetterName)) {
            return;
        }

        VALUE_GETTER_MAP.remove(valueGetterName);
    }

    @Override
    public synchronized void removeValueGetter(ISieveValueGetter valueGetter) {
        if (ObjectUtil.isEmpty(valueGetter)) {

            return;
        }

        removeValueGetter(valueGetter.getName());
    }

}
