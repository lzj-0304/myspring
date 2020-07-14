package com.lzj.spring.beans.factory.xml;

import com.lzj.spring.beans.BeanDefinition;
import com.lzj.spring.beans.factory.BeanDefinitionStoreException;
import com.lzj.spring.beans.support.BeanDefinitionRegistry;
import com.lzj.spring.beans.support.GenericBeanDefinition;
import com.lzj.spring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReader {
    public static final String ID_ATTRIBUTE="id";
    public static final String CLASS_ATTRIBUTE="class";
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(String configFile) {
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
                beanDefinitionRegistry.registerBeanDefinition(beanID,beanDefinition);
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
}
