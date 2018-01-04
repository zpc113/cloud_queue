package com.zpc.listener;

import com.alibaba.fastjson.JSONObject;
import com.zpc.dao.controlcenter.ScheduleDao;
import com.zpc.dao.controlcenter.TaskInfoDao;
import com.zpc.dto.*;
import com.zpc.entity.controlcenter.Schedule;
import com.zpc.queue.Queue;
import com.zpc.send.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 和谐社会人人有责 on 2017/11/29.
 */
@Component
public class MessageReceiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Queue queue;
    @Autowired
    private SendMessage sendMessage;
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private TaskInfoDao taskInfoDao;


    public void onMessage(Message message) {
        try {
            // 反序列化，获得对应的对象
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getBody());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            OrderMessage orderMessage = (OrderMessage) objectInputStream.readObject();
            // 关闭流
            byteArrayInputStream.close();
            objectInputStream.close();
            // 判断指令 并进行对应操作
            String order = orderMessage.getOrder();
            // 队列名
            String queueName = orderMessage.getContainerName();
            // 任务id
            long taskId = Long.parseLong(queueName.replace("QueueName" , ""));
            if (TaskOrder.RUN.equals(order)) {
                if (queue.getQueueMap() == null) {
                    Map<String , BlockingQueue<Request>> queueMap = new HashMap<String, BlockingQueue<Request>>();
                    queue.setQueueMap(queueMap);
                }
                // 新建队列并插入种子
                Request request = orderMessage.getRequest();
                BlockingQueue<Request> blockingQueue = new LinkedBlockingDeque<Request>();
                logger.info("新建队列" + queueName);
                try {
                    blockingQueue.put(request);
                    queue.getQueueMap().put(queueName , blockingQueue);
                    logger.info("种子入队列" + request.toString());
                } catch (InterruptedException e) {
                    logger.error(e.getMessage() + "种子入队列失败---->" + JSONObject.toJSONString(orderMessage), e);
                }
                // 新建调度
                Schedule schedule = new Schedule();

                schedule.setTaskId(taskId);
                schedule.setStartTime(new Date());
                schedule.setSurplusNum(1);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dt = sdf.format(new Date());
                schedule.setDt(dt);
                long scheduleId = scheduleDao.create(schedule);

                // 通知下载服务器下载种子页面
                orderMessage.setOrder(ControlExecutorOrder.SEED);
                String containerName = orderMessage.getContainerName() + "_scheduleId_" + scheduleId;
                orderMessage.setContainerName(containerName);
                sendMessage.sendDownMessage(orderMessage , RoutingKey.DOWNSERVICE_ROUTINGKEY);

                // 操作数据库，设置任务运行状态为运行中
                taskInfoDao.updateRunStatus(taskId , 1);
                logger.info("任务运行状态设置为运行中,taskId : " + taskId);
            } else if (TaskOrder.STOP.equals(order)) {
                // 删除队列
                this.destroy(queueName , taskId);
            } else if (TaskOrder.REQUEST.equals(order)) {
                // request入队列
                BlockingQueue<Request> blockingQueue = queue.getQueueMap().get(orderMessage.getContainerName());
                try {
                    blockingQueue.put(orderMessage.getRequest());
                    logger.info("request入队列成功" + orderMessage.toString());
                } catch (InterruptedException e) {
                    logger.error(e.getMessage() , "request入队列失败" + orderMessage.toString());
                }
            } else if (ControlExecutorOrder.READY.equals(order)) {
                // 下载线程池已初始化完毕，通知下载线程池开始获取request
                OrderMessage orderMessageToDownload = new OrderMessage();
                orderMessageToDownload.setOrder(ControlExecutorOrder.DOWNLOAD);
                orderMessageToDownload.setContainerName(orderMessage.getContainerName());
                sendMessage.sendDownMessage(orderMessageToDownload , RoutingKey.DOWNSERVICE_ROUTINGKEY);
                logger.info("已通知下载服务器开始下载页面" + orderMessage.getContainerName());
            } else if (ControlExecutorOrder.COMPLETE.equals(order)) {
                // 任务已完成，销毁队列
                this.destroy(queueName , taskId);
            }
        } catch (IOException e) {
            logger.error(e.getMessage() , "接收消息失败");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage() , "接收消息失败");
        }

    }

    /**
     * 销毁队列公共方法
     * @param queueName
     * @param taskId
     */
    public void destroy(String queueName , long taskId) {
        // 删除队列
        queue.getQueueMap().remove(queueName);
        logger.info("已移除队列" + queueName);
        // 立即进行垃圾回收，销毁该队列
        System.gc();
        taskInfoDao.updateRunStatus(taskId , 0);
        logger.info("任务运行状态设置为已完成,taskId : " + taskId);
        // 设置调度已完成
        Schedule schedule = scheduleDao.getSchedule(taskId);
        scheduleDao.setEnd(new Date() , schedule.getScheduleId());
    }
}
