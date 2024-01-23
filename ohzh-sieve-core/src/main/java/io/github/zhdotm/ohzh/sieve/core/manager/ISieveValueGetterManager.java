package io.github.zhdotm.ohzh.sieve.core.manager;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import io.github.zhdotm.ohzh.sieve.core.getter.value.ISieveValueGetter;

/**
 * @author zhihao.mao
 */

public interface ISieveValueGetterManager {

    /**
     * 值获取器管理器
     *
     * @return 值获取器管理器
     */
    default String getName() {

        return StrUtil.lowerFirst(ClassUtil.getClassName(this, Boolean.TRUE));
    }

    /**
     * 添加值获取器
     *
     * @param valueGetter 值获取器
     */
    void putValueGetter(ISieveValueGetter valueGetter);

    /**
     * 获取值获取器
     *
     * @param valueGetterName 值获取器名称
     * @return 值获取器
     */
    ISieveValueGetter getValueGetter(String valueGetterName);

    /**
     * 移除值获取器
     *
     * @param valueGetterName 值获取器名称
     */
    void removeValueGetter(String valueGetterName);

    /**
     * 移除值获取器
     *
     * @param valueGetter 值获取器
     */
    void removeValueGetter(ISieveValueGetter valueGetter);
}
