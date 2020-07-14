package com.lzj.spring.beans.support;

import com.lzj.spring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
    public  String  beanID;
    private String beanClassName;

    public GenericBeanDefinition(String beanID, String beanClassName) {
        this.beanID = beanID;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }
}
