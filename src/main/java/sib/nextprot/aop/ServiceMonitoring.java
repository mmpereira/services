package sib.nextprot.aop;
import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.istack.internal.NotNull;


/**
 * @author Daniel Teixeira
 * @version $Revision$, $Date$, $Author$
 */
public class ServiceMonitoring implements MethodInterceptor{
	
  public static final Log LOGGER = LogFactory.getLog(ServiceMonitoring.class);

	@Override
	public Object invoke(MethodInvocation methodinvocation) throws Throwable {
		
		  String methodName = methodinvocation.getMethod().getName();
		  Annotation[][] annotations = methodinvocation.getMethod().getParameterAnnotations();
		  Object[] arguments = methodinvocation.getArguments();
	      
	      for(int i=0; i<annotations.length; i++){
	          for(Annotation parameterAnnotation : annotations[i]){
	        	  if(parameterAnnotation.annotationType().equals(NotNull.class)){
	        		  if(arguments[i] == null){
	        			  throw new IllegalArgumentException("Expected a not null parameter" + methodName);
	        		  }
	        	  }
	          }
	      }
	      
	      //Invokes the method
	      return methodinvocation.getMethod().invoke(methodinvocation.getThis(), arguments);

	}
	  
}


