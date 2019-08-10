package com.laifeng.cpsjobs.boot;

import com.laifeng.cpsjobs.handler.TestHandler;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by zhengcunwen on 2015/6/26.
 */
public class TestBoot {

    public static void main(String[] args) {
        GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext("classpath:applicationContext-*.xml");
        TestHandler testHelper = applicationContext.getBean(TestHandler.class);

        testHelper.execute();

        System.exit(-1);
    }

}
