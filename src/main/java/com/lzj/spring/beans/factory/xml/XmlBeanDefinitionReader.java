package com.lzj.spring.beans.factory.xml;

import com.lzj.spring.beans.BeanDefinition;
import com.lzj.spring.beans.factory.BeanDefinitionStoreException;
import com.lzj.spring.beans.support.BeanDefinitionRegistry;
import com.lzj.spring.beans.support.GenericBeanDefinition;
import com.lzj.spring.core.io.Resource;
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
    public static final String SCOPE_ATTRIBUTE="scope";
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream is =null;
        try {
            is=  resource.getInputStream();
            SAXReader reader =new SAXReader();
            Document document = reader.read(is);
            Element element= document.getRootElement();
            Iterator<Element> elements= element.elementIterator();
            while (elements.hasNext()){
                element = elements.next();
                String beanID= element.attributeValue(ID_ATTRIBUTE);
                String beanClassName=element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = (BeanDefinition) new GenericBeanDefinition(beanID,beanClassName);
                if(element.attributeValue(SCOPE_ATTRIBUTE) !=null){
                    beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }
                beanDefinitionRegistry.registerBeanDefinition(beanID,beanDefinition);
            }
        } catch (Exception e) {
            throw  new BeanDefinitionStoreException(resource.getDescription()+"文件解析异常",e);
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
