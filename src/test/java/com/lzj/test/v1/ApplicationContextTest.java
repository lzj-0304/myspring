package com.lzj.test.v1;

import com.lzj.service.TestService;
import com.lzj.spring.context.ApplicationContext;
import com.lzj.spring.context.support.ClasspathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void test01(){
        ApplicationContext ctx = new ClasspathXmlApplicationContext("spring_v1.xml");
        TestService testService = (TestService)ctx.getBean("testService");
        Assert.assertNotNull(testService);
     }
}