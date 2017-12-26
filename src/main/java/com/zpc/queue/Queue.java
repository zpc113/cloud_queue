package com.zpc.queue;

import com.zpc.dto.Request;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 和谐社会人人有责 on 2017/12/5.
 * 队列类
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Queue {

    private Map<String , BlockingQueue<Request>> queueMap;

    public Map<String, BlockingQueue<Request>> getQueueMap() {
        return queueMap;
    }

    public void setQueueMap(Map<String, BlockingQueue<Request>> queueMap) {
        this.queueMap = queueMap;
    }
}
