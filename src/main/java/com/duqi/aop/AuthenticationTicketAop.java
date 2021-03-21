package com.duqi.aop;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class AuthenticationTicketAop {

  ThreadLocal<Long> startTime = new ThreadLocal<>();


  @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
  public void controllerGetMappingPointcut() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
  public void controllerPostMappingPointcut() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
  public void controllerDeleteMappingPointcut() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
  public void controllerPutMappingPointcut() {
  }

  @Before("controllerGetMappingPointcut()")
  public void getAuthenticationTicketFormGetMapping(JoinPoint joinPoint)
      throws AuthenticationException {
    startTime.set(System.currentTimeMillis());
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    String pin =
        request.getHeader("X-Consumer-Username") != null ? request.getHeader("X-Consumer-Username")
            : request.getHeader("x-credential-username");
//    if (    == null) {
//      throw new AuthenticationException();
//    }
    AuthenticationTicket.setTicket(new AuthenticationTicket(pin));
  }

  @Before("controllerPostMappingPointcut()")
  public void getAuthenticationTicketFormPostMapping(JoinPoint joinPoint)
      throws AuthenticationException {
    startTime.set(System.currentTimeMillis());
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    String pin =
        request.getHeader("X-Consumer-Username") != null ? request.getHeader("X-Consumer-Username")
            : request.getHeader("x-credential-username");
//    if (pin == null) {
//      throw new AuthenticationException();
//    }
    AuthenticationTicket.setTicket(new AuthenticationTicket(pin));
  }

  @Before("controllerDeleteMappingPointcut()")
  public void getAuthenticationTicketFormDeleteMapping(JoinPoint joinPoint)
      throws AuthenticationException {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    String pin =
        request.getHeader("X-Consumer-Username") != null ? request.getHeader("X-Consumer-Username")
            : request.getHeader("x-credential-username");
//    if (pin == null) {
//      throw new AuthenticationException();
//    }
    AuthenticationTicket.setTicket(new AuthenticationTicket(pin));
  }

  @Before("controllerPutMappingPointcut()")
  public void getAuthenticationTicketFormPutMapping(JoinPoint joinPoint)
      throws AuthenticationException {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    String pin =
        request.getHeader("X-Consumer-Username") != null ? request.getHeader("X-Consumer-Username")
            : request.getHeader("x-credential-username");
//    if (pin == null) {
//      throw new AuthenticationException();
//    }
    AuthenticationTicket.setTicket(new AuthenticationTicket(pin));
  }
  @After("controllerPostMappingPointcut()")
  public void doAfter(){
    System.out.println(System.currentTimeMillis()-startTime.get());
  }
}
