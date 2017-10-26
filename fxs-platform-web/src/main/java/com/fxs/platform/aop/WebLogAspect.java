package com.fxs.platform.aop;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Charles
 *
 */

@Aspect
@Component
@Order(1)
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Around("execution(* com.fxs.platform.web.*.*.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(pjp.getArgs()));

		long start = new Date().getTime();

		Object object = pjp.proceed();

		logger.info("RESPONSE : " + object);
		logger.info("SPEND TIME : " + (new Date().getTime() - start));

		return object;
	}
}
