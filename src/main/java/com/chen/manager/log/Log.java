package com.chen.manager.log;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * log的封装
 * <p>
 * created at 2019-12-19
 *
 * @author MonoWing
 */

public class Log {

    /**
     * 所有类的logger对象
     */
    private static Map<String, Logger> loggerMap = new HashMap<String, Logger>();

    public static void debug(Object message) {
        String className = getClassName();
        Logger log = getLogger(className);
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }

    public static void debug(String tag, Object message) {
        String className = getClassName();
        Logger log = getLogger(className);
        if (log.isDebugEnabled()) {
            log.debug(new StringBuffer().append("【").append(tag).append("】")
                    .append(message).toString());
        }
    }

    public static void info(Object message) {
        String className = getClassName();
        Logger log = getLogger(className);
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }

    public static void info(String tag, Object message) {
        String className = getClassName();
        Logger log = getLogger(className);
        if (log.isInfoEnabled()) {
            log.info(new StringBuffer().append("【").append(tag).append("】")
                    .append(message).toString());
        }
    }

    public static void warn(Object message) {
        String className = getClassName();
        Logger log = getLogger(className);
        log.warn(message);
    }

    public static void warn(String tag, Object message) {
        String className = getClassName();
        Logger log = getLogger(className);
        log.warn(new StringBuffer().append("【").append(tag).append("】")
                .append(message).toString());
    }

    public static void error(Object message) {
        String className = getClassName();
        Logger log = getLogger(className);
        log.error(message);
    }

    public static void error(String tag, Object message) {
        String className = getClassName();
        Logger log = getLogger(className);
        log.error(new StringBuffer().append("【").append(tag).append("】")
                .append(message).toString());
    }

    /**
     * 获取最开始的调用者所在类
     *
     * @return
     */
    private static String getClassName() {
        Throwable th = new Throwable();
        StackTraceElement[] stes = th.getStackTrace();
        StackTraceElement ste = stes[2];
        return ste.getClassName();
    }

    /**
     * 根据类名获得logger对象
     *
     * @param className
     * @return
     */
    private static Logger getLogger(String className) {
        Logger log = null;
        if (loggerMap.containsKey(className)) {
            log = loggerMap.get(className);
        } else {
            try {
                log = LogManager.getLogger(Class.forName(className));
                loggerMap.put(className, log);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return log;
    }
}
