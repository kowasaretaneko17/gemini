package com.hekiraku.gemini.common;

import com.hekiraku.gemini.common.utils.ApplicationContextHolder;
import com.hekiraku.gemini.entity.ActiveLogEntity;
import com.hekiraku.gemini.manager.ActiveLogManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.List;

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
