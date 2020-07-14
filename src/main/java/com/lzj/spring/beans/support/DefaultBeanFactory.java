package com.lzj.spring.beans.support;

import com.lzj.spring.beans.BeanDefinition;
import com.lzj.spring.beans.factory.BeanCreationException;
import com.lzj.spring.beans.factory.config.ConfigurableBeanFactory;
import com.lzj.spring.util.ClassUtils;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory,BeanDefinitionRegistry {

    public Map<String, BeanDefinition> beanDefinitionMap=new HashMap<String,BeanDefinition>();
    private ClassLoader beanClassLoader;


    @Override
    public Object getBean(String beanID) {
        BeanDefinition beanDefinition =beanDefinitionMap.get(beanID);
        if(null == beanDefinition){
            return null;
        }
        // 如果Bean scope 为 Singleton
        if(beanDefinition.isSingleton()){
            Object bean = this.getSingleton(beanID);
            if(null == bean){
                bean=createBean(beanDefinition);
                this.registerSingleton(beanID,bean);
            }
            return bean;
        }
        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        try {
            String beanClassName= beanDefinition.getBeanClassName();
            return this.getBeanClassLoader().loadClass(beanClassName).newInstance();
        } catch (Exception e) {
            throw  new BeanCreationException("创建"+beanDefinition.getBeanClassName()+"失败",e);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }


    @Override
    public void registerBeanDefinition(String beanID, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanID,beanDefinition);
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
