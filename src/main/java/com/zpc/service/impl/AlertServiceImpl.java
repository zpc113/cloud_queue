package com.zpc.service.impl;

import com.zpc.service.AlertService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 和谐社会人人有责 on 2017/11/28.
 */
@Service
public class AlertServiceImpl implements AlertService{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessageTest(String message) {

        rabbitTemplate.convertAndSend("queueExecuteKey", message);
    }
}
