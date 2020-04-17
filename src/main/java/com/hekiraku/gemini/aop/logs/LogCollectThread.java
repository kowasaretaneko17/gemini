package com.hekiraku.gemini.aop.logs;

import com.hekiraku.gemini.common.utils.ApplicationContextHolder;
import com.hekiraku.gemini.domain.entity.ActiveLogEntity;
import com.hekiraku.gemini.manager.ActiveLogManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志收集线程
 * 
 * @author qinyunsen
 *
 */
@Slf4j
public class LogCollectThread implements Runnable {

	private ActiveLogEntity activityLog;
	/**
	 * 传入封装好的日志对象
	 * 
	 * @param activelog
	 */
	public LogCollectThread(ActiveLogEntity activelog) {
		this.activityLog = activelog;
	}

	@Override
	public void run() {
		ActiveLogManager activeLogManager = (ActiveLogManager) ApplicationContextHolder.getBean("activeLogManager");
		int result = activeLogManager.create(activityLog);
		if(1==result){
			log.info("操作日志插入数据库成功：{}",activityLog);
		}else{
			log.error("操作日志插入数据库失败：{}",activityLog);
		}
	}

}
