package com.hekiraku.gemini.aop.logs;

import com.hekiraku.gemini.common.enums.LogActiveNameEnums;
import com.hekiraku.gemini.common.enums.LogActiveProjectEnums;
import com.hekiraku.gemini.common.enums.LogActiveTypeEnums;
import com.hekiraku.gemini.entity.ActiveLogEntity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogAgent {

    /**
     * 日志任务队列
     */
    private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(100);

    /**
     * 日志任务线程池,初始化3个线程，最大100个任务，最大启动10个线程，线程缓存一分钟
     */
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 10, 1, TimeUnit.MINUTES, queue);

    private LogAgent() {
    }

    /**
     * 日志收集方法
     *
     */
    public static void log(LogActiveProjectEnums project, LogActiveTypeEnums logType, String bussId,
            LogActiveNameEnums activeName,String activeDesc) {
        log( project,  logType, bussId, activeName,activeDesc, "","");
    }

    /**
     * 日志收集方法
     *
     */
        public static void log(LogActiveProjectEnums project, LogActiveTypeEnums logType, String bussId,
            LogActiveNameEnums activeName,String activeDesc, String activeMethod, String activeData) {

        if (logType == null || project == null) {
            return;
        }
        ActiveLogEntity activeLog = new ActiveLogEntity
                .Builder(project,logType,bussId,activeName,activeDesc)
                .activeData(activeData)
                .activeMethod(activeMethod)
                .createTime(new java.util.Date())
                .createUserId("1")
                .build();
        log(activeLog);
    }

    /**
     * 日志收集方法
     *
     * @param activelog
     */
    public static void log(ActiveLogEntity activelog) {
        Runnable collectThread = new LogCollectThread(activelog);
        pool.execute(collectThread);
    }

}

