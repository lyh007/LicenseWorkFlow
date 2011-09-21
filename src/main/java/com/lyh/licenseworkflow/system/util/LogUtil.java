package com.lyh.licenseworkflow.system.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 日志工具
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-20下午1:36
 * @Email liuyuhui007@gmail.com
 */
public class LogUtil {
    //系统模块日志
    public static final String SYSTEM_LOG = "systemLog";

    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }
}
