package com.lzj.spring.beans.support;

import com.lzj.spring.beans.factory.config.SingletonBeanRegistry;
import com.lzj.spring.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> beanMap=new ConcurrentHashMap<String,Object>();
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "'beanName' must not be null");
        Object bean = this.beanMap.get(beanName);
        if(null !=bean){
            // bean 对象已注册 不可重复注册
            throw new IllegalStateException(beanName+"对象无法注册,该对象已存在!");
        }
        this.beanMap.put(beanName,singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return beanMap.get(beanName);
    }
}
