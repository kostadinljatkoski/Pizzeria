package mk.ukim.finki.wp.pizzeria.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodCallAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* mk.ukim.finki.wp.pizzeria..*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info("[WP-Log] Requested class: " + joinPoint.getTarget().getClass().getName() + "; Method: " +
                joinPoint.getSignature().getName());
    }

}
