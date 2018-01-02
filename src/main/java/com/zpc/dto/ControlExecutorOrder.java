package com.zpc.dto;

/**
 * Created by 和谐社会人人有责 on 2017/12/5.
 * 控制线程池指令
 */
public class ControlExecutorOrder {

    public static final String NEW = "new";    // 新建线程池指令

    public static final String SUSPEND = "suspend";    // 暂停线程池指令

    public static final String DESTROY = "destroy";   // 销毁线程池指令

    public static final String SEED = "seed";   // 下载种子页面

    public static final String READY = "ready";   // 线程池初始化完成

    public static final String DOWNLOAD = "download";   // 线程池初始化完成

    public static final String COMPLETE = "complete";   // 任务完成指令


}
