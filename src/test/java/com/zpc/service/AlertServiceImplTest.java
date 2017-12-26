package com.zpc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 和谐社会人人有责 on 2017/11/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml" ,
        "classpath:spring/spring-rabbitMQ.xml"})
public class AlertServiceImplTest {

    @Autowired
    private AlertService alertService;

    @Test
    public void sendMessageTest() throws Exception {
        alertService.sendMessageTest("----------------------消息发送测试-----------------------");
    }

}