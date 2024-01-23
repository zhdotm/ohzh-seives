package io.github.zhdotm.ohzh.sieve.starter.web.getter;

import io.github.zhdotm.ohzh.sieve.core.getter.value.ISieveValueGetter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * @author zhihao.mao
 */

public interface ISpringSieveValueGetter extends ISieveValueGetter, BeanNameGenerator {

    @Override
    default String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {

        return getName();
    }

}
