package com.lzj.spring.beans;

import java.util.List;

public interface BeanDefinition {

    String getBeanClassName();

    public static final String SCOPE_SINGLETON = "singleton";

    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT="";
    // 获取scope 作用域
    String getScope();
    // 设置作用域
    void setScope(String scope);

    //快速定位bean scope类型singleton
    public boolean isSingleton();
    //快速定位bean scope类型 prototype
    public boolean isPrototype();

    public List<PropertyValue> getPropertyValues();
}
