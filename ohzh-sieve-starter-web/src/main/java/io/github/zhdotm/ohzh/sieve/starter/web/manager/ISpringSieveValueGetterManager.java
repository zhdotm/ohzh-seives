package io.github.zhdotm.ohzh.sieve.starter.web.manager;

import io.github.zhdotm.ohzh.sieve.core.manager.ISieveValueGetterManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * @author zhihao.mao
 */

public interface ISpringSieveValueGetterManager extends ISieveValueGetterManager, BeanNameGenerator {

    @Override
    default String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {

        return getName();
    }

}
