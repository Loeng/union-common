package com.union.validate.autoconfigure;


import com.union.validate.BindingValidator;
import com.union.validate.ValidatorScanner;
import com.union.validate.resolver.CheckMethodArgumentResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author GaoWei
 * @describe
 * @time 2017/12/26,10:23
 */
@Configuration
public class ValidateAutoConfiguration extends WebMvcConfigurerAdapter implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new CheckMethodArgumentResolver());
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        ValidatorScanner pluginPathScanner = new ValidatorScanner(beanDefinitionRegistry);
        pluginPathScanner.setMarkerInterface(BindingValidator.class);
        pluginPathScanner.registerFilters();
        pluginPathScanner.doScan("com.union.validate.validator");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }
}
