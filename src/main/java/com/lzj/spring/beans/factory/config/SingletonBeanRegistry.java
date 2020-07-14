package com.lzj.spring.beans.factory.config;

public interface SingletonBeanRegistry {
    // 注册单例Bean对象
    void registerSingleton(String beanName, Object singletonObject);
    //获取单例Bean对象
    Object getSingleton(String beanName);
}
