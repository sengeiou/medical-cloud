package com.zhou.medical.log.aspect;


import com.google.gson.Gson;
import com.zhou.medical.log.annotation.SystemControllerLog;
import com.zhou.medical.log.entity.RequestLog;
import com.zhou.medical.log.sender.RequestLogSender;
import com.zhou.medical.log.util.EmojiFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

/**
 * 切面保存请求日志..
 * @author luoyizhou
 *
 *
 */
@Component
@Aspect
public class RequestLogAspect {

	private final static Log logger = LogFactory.getLog(RequestLogAspect.class);
	private RequestLog requestLog;
	private Gson gson = new Gson();

	@Autowired
	private RequestLogSender requestLogSender;


	@Pointcut("@annotation(com.zhou.medical.log.annotation.SystemControllerLog)")
	public void controllerAspect() {

	}

	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			String serverIp = request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort();
			String clientIp = getIpAddrByRequest(request);
			requestLog = new RequestLog();
			long nanoTime =  System.nanoTime();
			requestLog.setRequestLodId(nanoTime);
			requestLog.setState(0);
			requestLog.setCreateTime(new Date());
			requestLog.setClientIp(clientIp);
			requestLog.setServerIp(serverIp);
			String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
			requestLog.setUrl(requestUrl);

			Enumeration<String> pNames = request.getParameterNames();
			String requestString = "";
			while (pNames.hasMoreElements()) {
				String name = pNames.nextElement();
				String value = request.getParameter(name);
				requestString = requestString + name + "=" + value + "&";
			}

			requestLog.setRequest(requestString);
			try {
				String actionName = getControllerMethodDescription(joinPoint);
				requestLog.setActionName(actionName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String methodName = joinPoint.getSignature().getName();
			requestLog.setMethodName(methodName);
			String className = joinPoint.getTarget().getClass().getSimpleName();
			requestLog.setClassName(className);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String request = requestLog.getRequest();
				request = EmojiFilter.filterEmoji(request);
				requestLog.setRequest(request);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	@AfterReturning(value = "controllerAspect()", returning = "results")
	public void afterReturning(JoinPoint joinpoint, Object results) throws Throwable {
		try {
			String resultString = gson.toJson(results);
			requestLog.setState(1);
			requestLog.setResponse(resultString);
			requestLog.setUpdateTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			requestLogSender.send(requestLog);
		}
		
	}

	@AfterThrowing(value = "controllerAspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
		try {
			String exString = throwableToString(ex);
			requestLog.setState(99);
			requestLog.setResponse(exString);
			requestLog.setUpdateTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			logger.info("requestLog------------------:"+gson.toJson(requestLog));
			requestLogSender.send(requestLog);
		}
	}

	/**
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	private static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).value();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	private String throwableToString(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}


	public static String getIpAddrByRequest(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
