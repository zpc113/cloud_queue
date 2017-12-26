package com.zpc.entity.controlcenter;

import java.util.Date;

/**
 * Created by 和谐社会人人有责 on 2017/11/23.
 * 任务调度
 */
public class Schedule {
    private long taskId;

    private long scheduleId;

    private Date startTime;

    private Date endTime;

    private String dt;
    // 成功页面熟
    private long successNum;
    // 失败页面数
    private long failedNum;
    // 总请求量
    private long requestNum;
    // 剩余队列数
    private long surplusNum;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public long getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(long successNum) {
        this.successNum = successNum;
    }

    public long getFailedNum() {
        return failedNum;
    }

    public void setFailedNum(long failedNum) {
        this.failedNum = failedNum;
    }

    public long getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(long requestNum) {
        this.requestNum = requestNum;
    }

    public long getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(long surplusNum) {
        this.surplusNum = surplusNum;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "taskId=" + taskId +
                ", scheduleId=" + scheduleId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", dt='" + dt + '\'' +
                ", successNum=" + successNum +
                ", failedNum=" + failedNum +
                ", requestNum=" + requestNum +
                ", surplusNum=" + surplusNum +
                '}';
    }

    public Schedule() {
    }

    public Schedule(long taskId, long scheduleId, Date startTime, Date endTime, String dt, long successNum, long failedNum, long requestNum, long surplusNum) {
        this.taskId = taskId;
        this.scheduleId = scheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dt = dt;
        this.successNum = successNum;
        this.failedNum = failedNum;
        this.requestNum = requestNum;
        this.surplusNum = surplusNum;
    }
}
