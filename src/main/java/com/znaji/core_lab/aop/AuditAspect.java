package com.znaji.core_lab.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

        private static final Logger log = LoggerFactory.getLogger(AuditAspect.class);

        @Pointcut("execution(* com.znaji.core_lab.service..*(..)) && !within(com.znaji.core_lab.aop..*)")
        public void serviceMethods() {
        }

        @Around("serviceMethods()")
        public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
                if (joinPoint.getSignature().getName().equals("internalWork")) {
                        log.debug("Skipping advice for internalWork to highlight self-invocation gotcha");
                }
                log.debug("Before {}", joinPoint.getSignature());
                try {
                        Object result = joinPoint.proceed();
                        log.debug("After {} result {}", joinPoint.getSignature(), result);
                        return result;
                }
                catch (Throwable ex) {
                        log.error("Exception in {}", joinPoint.getSignature(), ex);
                        throw ex;
                }
        }
}
