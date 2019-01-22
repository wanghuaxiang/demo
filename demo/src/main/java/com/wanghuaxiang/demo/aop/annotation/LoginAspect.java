package com.wanghuaxiang.demo.aop.annotation;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author: 王华翔
 * @version: 创建时间：2018年12月31日 下午11:01:32
 * @description:
 */
@Aspect
@Component
public class LoginAspect {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(public * com.wanghuaxiang.**.controller.*.*(..))&&@annotation(com.wanghuaxiang.demo.aop.annotation.NeedLogin)")
	public void needLoginPoint() {
	}

	@Before("needLoginPoint()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {

		// 记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

	}

	@Around("needLoginPoint()")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		logger.info("目标方法名：" + proceedingJoinPoint.getSignature().getName());
		logger.info("do login ...");
		try {
			Object obj = proceedingJoinPoint.proceed();
			return obj;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return null;
	}

}
