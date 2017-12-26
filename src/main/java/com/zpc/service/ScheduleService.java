package com.zpc.service;

import com.zpc.entity.controlcenter.Schedule;

import java.util.Date;
import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/11/23.
 */
public interface ScheduleService {

    /**
     * 根据taskId查询所有调度
     * @return
     */
    List<Schedule> findAll(long taskId);

    /**
     * 新建调度
     * @param schedule
     */
    long create(Schedule schedule);

    /**
     * 更新调度结束时间
     * @param endTime
     * @param scheduleId
     */
    void setEnd(Date endTime, long scheduleId);

}
