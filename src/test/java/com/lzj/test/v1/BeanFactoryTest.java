package com.lzj.test.v1;

import com.lzj.spring.beans.BeanDefinition;
import com.lzj.spring.beans.factory.BeanCreationException;
import com.lzj.spring.beans.factory.BeanDefinitionStoreException;
import com.lzj.spring.beans.factory.BeanFactory;
import com.lzj.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lzj.spring.beans.support.DefaultBeanFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeanFactoryTest {
   /* @Test
    public void test01(){
        BeanFactory factory=new DefaultBeanFactory("spring_v1.xml");
        BeanDefinition beanDefinition = factory.getBeanDefinition("testService");
        assertEquals("com.lzj.service.TestService",beanDefinition.getBeanClassName());
        assertNotNull(factory.getBean("testService"));
    }

    @Test
    public void test02(){
        try {
            BeanFactory factory=new DefaultBeanFactory("spring_v2.xml");
        } catch (BeanDefinitionStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03(){
        try {
            BeanFactory factory=new DefaultBeanFactory("spring_v1.xml");
            factory.getBean("testService2");
        } catch (BeanCreationException e) {
            e.printStackTrace();
        }
    }*/


   /* @Test
    public void test04(){
        DefaultBeanFactory factory=new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("spring_v1.xml");
        BeanDefinition beanDefinition=factory.getBeanDefinition("testService");
        assertEquals("com.lzj.service.TestService",beanDefinition.getBeanClassName());
        assertNotNull(factory.getBean("testService"));
    }*/

}
