package com.lzj.spring.context.support;

import com.lzj.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lzj.spring.beans.support.DefaultBeanFactory;
import com.lzj.spring.context.ApplicationContext;
import com.lzj.spring.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory=null;

    public AbstractApplicationContext(String configFile) {
        factory=new DefaultBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(this.getResourceByPath(configFile));
    }

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }
    // 变化资源代码交给子类传入
    public abstract Resource getResourceByPath(String configFile);
}
