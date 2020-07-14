package com.lzj.spring.context.support;

import com.lzj.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lzj.spring.beans.support.DefaultBeanFactory;
import com.lzj.spring.context.ApplicationContext;
import com.lzj.spring.core.io.Resource;
import com.lzj.spring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory=null;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        factory=new DefaultBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(this.getResourceByPath(configFile));
        factory.setBeanClassLoader(this.beanClassLoader);
    }

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }
    // 变化资源代码交给子类传入
    public abstract Resource getResourceByPath(String configFile);

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }
    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
