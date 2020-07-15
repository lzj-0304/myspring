package com.lzj.test.v2;

import com.lzj.dao.TestDao;
import com.lzj.service.TestService;
import com.lzj.spring.context.ApplicationContext;
import com.lzj.spring.context.support.ClasspathXmlApplicationContext;
import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationContextTest {

    @Test
    public void test01(){
        ApplicationContext ac=new ClasspathXmlApplicationContext("spring_v2.xml");
        TestService testService = (TestService) ac.getBean("testService");
        assertNotNull(testService);
        assertNotNull(testService.getTestDao());
    }

    @Test
    public void test02(){
        ApplicationContext ac=new ClasspathXmlApplicationContext("spring_v2.xml");
        TestService testService = (TestService) ac.getBean("testService");
        assertEquals("lzj",testService.getUname());
    }

    @Test
    public void test03(){
        ApplicationContext ac=new ClasspathXmlApplicationContext("spring_v2.xml");
        TestService testService = (TestService) ac.getBean("testService");
        assertEquals("lzj",testService.getUname());
        System.out.println(testService.getAge());
        System.out.println(testService.getFlag());
    }

}
