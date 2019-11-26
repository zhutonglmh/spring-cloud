package com.zt.demo.aopdemo.annotation;

import java.lang.annotation.*;

/**
 * @author zhutong
 * @date 2019/3/22 16:40
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String value();
}
