package com.zhou.medical.manager;

import java.lang.annotation.*;


/**
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface TestAnn {

    /**
     *
     * @return
     */
    String description()  default "";
    
} 
