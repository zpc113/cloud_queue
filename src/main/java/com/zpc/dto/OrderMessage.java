package com.zpc.dto;

import java.io.Serializable;

/**
 * Created by 和谐社会人人有责 on 2017/12/5.
 */
public class OrderMessage implements Serializable{

    private Request request;

    private String order;

    private String containerName;

    @Override
    public String toString() {
        return "OrderMessage{" +
                "request=" + request +
                ", order='" + order + '\'' +
                ", containerName='" + containerName + '\'' +
                '}';
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public OrderMessage() {
    }

    public OrderMessage(Request request, String order, String containerName) {
        this.request = request;
        this.order = order;
        this.containerName = containerName;
    }
}
