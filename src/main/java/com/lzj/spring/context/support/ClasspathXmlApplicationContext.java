package com.lzj.spring.context.support;

import com.lzj.spring.core.io.ClassPathResource;
import com.lzj.spring.core.io.Resource;

public class ClasspathXmlApplicationContext extends AbstractApplicationContext {

    public ClasspathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResourceByPath(String configFile) {
        return new ClassPathResource(configFile,this.getBeanClassLoader());
    }
}
