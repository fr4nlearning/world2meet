package com.example.sandav.infrastructure.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class NegativeIdAspect {

    @AfterReturning("execution(* com.example.sandav.infrastructure.rest.StarshipController.getById(..))")
    public void negativeId(JoinPoint joinPoint){

        var starshipId= (Integer) joinPoint.getArgs()[0];
        if(starshipId < 0)
            log.info("Attempt to obtain a ship with negative id: {}", starshipId);
    }
}
