package com.genebio.nextprot.aop;

import java.lang.annotation.Annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Daniel Teixeira
 * @version $Revision$, $Date$, $Author$
 */
@Aspect
public class ServiceMonitoring {
	
	private static final Log LOGGER = LogFactory.getLog(ServiceMonitoring.class);

	/**
	 * Logs information
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.genebio.nextprot.service.PublicationService.*(..))")
	public Object logInformaton(ProceedingJoinPoint pjp) throws Throwable {
	
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
		Object[] arguments = pjp.getArgs();
		
		StringBuilder builder = new StringBuilder();

		int i=0;
		for (Object o : arguments){
			Annotation[] annots = annotations[i];
			boolean foundValue = false;
			for(Annotation a : annots){
				if(!foundValue && a.annotationType().equals(Value.class)){
					Value v = (Value) a;
					builder.append(v.value());
					foundValue = true;
				}
			}

			i++;
			if(!foundValue){
				builder.append("arg" + i);
			}

			builder.append("=" + ((o!=null) ? o.toString() : "null"));
			builder.append("; ");
		}

		long start = System.currentTimeMillis();
		Object result =  pjp.proceed(arguments);
		String s = "Method " + methodSignature.getDeclaringType().getName() + "." + methodSignature.getName() + " was executed in " + (System.currentTimeMillis() - start) + " (ms) with arguments: " + builder.substring(0, builder.length()-1);
		System.out.println(s);
		LOGGER.info(s);
		return result;
		
	}
}