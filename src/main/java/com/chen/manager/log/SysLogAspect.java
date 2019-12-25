package com.chen.manager.log;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.chen.manager.annotation.OperLog;
import com.chen.manager.entity.SysLog;
import com.chen.manager.service.SysLogService;
import com.chen.manager.utils.BaseUtils;
import com.chen.manager.viewmodel.CommonResult;


/**
 * 系统日志：切面处理类
 * 
 * created at 2019-12-24
 * 
 * @author MonoWing
 *
 */
@Aspect
@Component
public class SysLogAspect {

	@Autowired
	private SysLogService sysLogService;

	/**
	 * 通过注解的方式来切入代码
	 */
	@Pointcut("@annotation( com.chen.manager.annotation.OperLog)")
	public void logPoinCut() {
	}

	/**
	 * 环绕通知
	 * @param proceedingJoinPoint
	 * @return
	 */
	@Around("logPoinCut()")
	public Object saveSysLog(ProceedingJoinPoint proceedingJoinPoint) {

		// 系统日志
		SysLog sysLog = new SysLog();

		// 从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) proceedingJoinPoint
				.getSignature();

		// 获取切入点所在的方法
		Method method = signature.getMethod();

		// 获取操作
		OperLog myLog = method.getAnnotation(OperLog.class);
		if (myLog != null) {
			// 保存获取的操作
			sysLog.setMessage(myLog.message());
			sysLog.setOperation(myLog.operation());
		}
		// 获取请求的类名
		String className = proceedingJoinPoint.getTarget().getClass().getName();
		// 获取请求的方法名
		String methodName = method.getName();
		sysLog.setMethod(className + "." + methodName);

		// 请求的参数
		Object[] args = proceedingJoinPoint.getArgs();
		// 将参数所在的数组转换成json
		String params = JSON.toJSONString(args);
		// 如果是登录操作，登录账号和密码会记录 TODO 需要修改
		sysLog.setParams(params);

		// 获取用户名
		// 获取用户IP地址
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		Log.info("URL : " + request.getRequestURL().toString());
		Log.info("HTTP_METHOD : " + request.getMethod());
		Log.info("IP : " + request.getRemoteAddr());
		Log.info("REAL_IP :" + BaseUtils.getRealIpAddress(request));
		sysLog.setIp(BaseUtils.getRealIpAddress(request));

		// 开始调用时间
		long start = System.currentTimeMillis();

		try {
			Object result = proceedingJoinPoint.proceed();

			Long time = System.currentTimeMillis() - start;
			sysLog.setTotalTime(time);

			// 保存日志
			sysLogService.save(sysLog);

			return result;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		return new CommonResult().error("系统异常");
	}

}
