package com.chen.manager.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志
 * <p>
 * created at 2019-12-24
 *
 * @author MonoWing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {

    /**
     * 介绍
     *
     * @return
     */
    String message();

    /**
     * 日志类型
     *
     * @return
     */
    String operation();
}
