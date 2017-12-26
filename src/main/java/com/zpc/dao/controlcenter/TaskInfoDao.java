package com.zpc.dao.controlcenter;

import com.zpc.entity.controlcenter.TaskInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/11/20.
 */
public interface TaskInfoDao {

    TaskInfo findById(long taskId);

    List<TaskInfo> findByName(String taskName);

    /**
     * 查找所有任务
     * @return
     */
    List<TaskInfo> findAll();

    /**
     * 新建任务
     * @param taskInfo
     */
    void create(TaskInfo taskInfo);

    void updateTaskInfo(@Param("taskName") String taskName, @Param("taskId") long taskId);

    /**
     * 删除任务
     * @param taskId
     */
    void delete(long taskId);

    /**
     * 更新运行时间
     * @param runTime
     */
    void updateRunTime(@Param("taskId") long taskId, @Param("runTime") Date runTime);

    /**
     * 更新运行状态
     * @param status
     */
    void updateRunStatus(@Param("taskId") long taskId, @Param("runStatus") int runStatus);

}
