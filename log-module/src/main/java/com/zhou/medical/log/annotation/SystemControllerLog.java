package com.zhou.medical.log.annotation;

import java.lang.annotation.*;


/**
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface SystemControllerLog {

    /**
     *
     * @return
     */
    String value()  default "";
    
} 
