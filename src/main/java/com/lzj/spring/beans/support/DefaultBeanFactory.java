package com.lzj.spring.beans.support;

import com.lzj.spring.beans.BeanDefinition;
import com.lzj.spring.beans.factory.BeanCreationException;
import com.lzj.spring.beans.factory.BeanDefinitionStoreException;
import com.lzj.spring.beans.factory.BeanFactory;
import com.lzj.spring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DefaultBeanFactory implements BeanFactory {

    public Map<String, BeanDefinition> beanDefinitionMap=new HashMap<String,BeanDefinition>();

    public static final String ID_ATTRIBUTE="id";
    public static final String CLASS_ATTRIBUTE="class";

    public DefaultBeanFactory(String configFile) {
        loadBeanDefinition(configFile);
    }

    private void loadBeanDefinition(String configFile) {
        InputStream is =null;
        try {
            is= ClassUtils.getDefaultClassLoader().getResourceAsStream(configFile);
            SAXReader reader =new SAXReader();
            Document document = reader.read(is);
            Element element= document.getRootElement();
            Iterator<Element> elements= element.elementIterator();
            while (elements.hasNext()){
                element = elements.next();
                String beanID= element.attributeValue(ID_ATTRIBUTE);
                String beanClassName=element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = (BeanDefinition) new GenericBeanDefinition(beanID,beanClassName);
                beanDefinitionMap.put(beanID,beanDefinition);
            }
        } catch (Exception e) {
            throw  new BeanDefinitionStoreException(configFile+"文件解析异常",e);
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
}
