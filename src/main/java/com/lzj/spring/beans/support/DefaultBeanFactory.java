package com.lzj.spring.beans.support;

import com.lzj.spring.beans.BeanDefinition;
import com.lzj.spring.beans.factory.BeanCreationException;
import com.lzj.spring.beans.factory.BeanFactory;
import com.lzj.spring.util.ClassUtils;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanFactory implements BeanFactory,BeanDefinitionRegistry {

    public Map<String, BeanDefinition> beanDefinitionMap=new HashMap<String,BeanDefinition>();



    @Override
    public Object getBean(String beanID) {
        BeanDefinition beanDefinition =beanDefinitionMap.get(beanID);
        if(null == beanDefinition){
            return null;
        }
        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        try {
            String beanClassName= beanDefinition.getBeanClassName();
            return ClassUtils.getDefaultClassLoader().loadClass(beanClassName).newInstance();
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
}
