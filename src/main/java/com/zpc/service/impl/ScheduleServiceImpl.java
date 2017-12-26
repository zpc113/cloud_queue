package com.zpc.service.impl;

import com.zpc.dao.controlcenter.ScheduleDao;
import com.zpc.entity.controlcenter.Schedule;
import com.zpc.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/12/3.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    public List<Schedule> findAll(long taskId) {
        return scheduleDao.findAll(taskId);
    }

    public long create(Schedule schedule) {
        return scheduleDao.create(schedule);
    }

    public void setEnd(Date endTime, long scheduleId) {
        scheduleDao.setEnd(endTime , scheduleId);
    }
}
