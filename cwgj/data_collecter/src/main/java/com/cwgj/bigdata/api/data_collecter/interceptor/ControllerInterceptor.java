package com.cwgj.bigdata.api.data_collecter.interceptor;


import com.cwgj.bigdata.api.data_collecter.vo.BaseVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerInterceptor {

  private final static Logger logger = LoggerFactory.getLogger("ControllerInterceptor");

  private final static String skey = "1";

  @Pointcut("execution(* com.cwgj.bigdata.api.data_collecter.controller..*(..))")
  public void controllerMethodPointcut() {
  }


  @Before("controllerMethodPointcut()")
  public void doBeforeAdvicePoint(JoinPoint joinPoint) throws Throwable {
    logger.debug("before------------------------------------");
    BaseVO o = (BaseVO) joinPoint.getArgs()[0];
    if (!o.isMD5Vaild(skey)) {
      logger.debug("go away");
      throw new Throwable("wrong sign");
    }
    logger.debug("come in");

  }

}
