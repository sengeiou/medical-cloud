package com.zhou.medical.common.annotation;

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
    String description()  default "";
    
} 
