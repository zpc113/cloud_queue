package com.zpc.web;

import com.zpc.dto.ControlExecutorOrder;
import com.zpc.dto.OrderMessage;
import com.zpc.dto.Request;
import com.zpc.dto.RoutingKey;
import com.zpc.queue.Queue;
import com.zpc.send.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/12/20.
 */
@Controller
@RequestMapping("/queue")
public class QueueController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Queue queue;
    @Autowired
    private SendMessage sendMessage;

    @RequestMapping(value = "/{queueName}/get" , method = RequestMethod.GET , produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public List<Request> getRequests(@PathVariable("queueName") String queueName) {
        List<Request> requests = new ArrayList<Request>();
        for (int i = 0 ; i < 10 ; i ++) {
            try {
                Request request = queue.getQueueMap().get(queueName.split("_scheduleId_")[0]).take();
                requests.add(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 通知下载服务器再次获取request
        OrderMessage orderMessageToDownload = new OrderMessage();
        orderMessageToDownload.setOrder(ControlExecutorOrder.DOWNLOAD);
        orderMessageToDownload.setContainerName(queueName);
        sendMessage.sendDownMessage(orderMessageToDownload , RoutingKey.DOWNSERVICE_ROUTINGKEY);
        logger.info("已通知下载服务器开始下载页面" + queueName);
        return requests;
    }


}
