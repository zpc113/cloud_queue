package com.zpc.entity.controlcenter;

import java.util.Date;

/**
 * Created by 和谐社会人人有责 on 2017/11/23.
 */
public class TaskInfo {
    private long taskId;

    private String taskName;

    private Date createTime;

    private Date runTime;

    private int runStatus;

    public TaskInfo(long taskId, String taskName, Date createTime, Date runTime, int runStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.createTime = createTime;
        this.runTime = runTime;
        this.runStatus = runStatus;
    }

    public TaskInfo() {
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", createTime=" + createTime +
                ", runTime=" + runTime +
                ", runStatus=" + runStatus +
                '}';
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public int getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(int runStatus) {
        this.runStatus = runStatus;
    }
}
