package com.chen.manager.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.chen.manager.log.Log;

/**
 * 基本方法类
 * 
 * created at 2019-12-03
 * 
 * @author Administrator
 *
 */
public class BaseUtils {

	/**
	 * id的字符串拼接转化为id的list
	 * 
	 * @param ids
	 *            id的字符串拼接，以","分隔
	 * @return
	 */
	public static List<Long> convertStringToLong(String ids) {

		if (StringUtils.isEmpty(ids)) {
			return null;
		}

		String[] split = ids.split(",");
		if (split == null || split.length <= 0) {
			return null;
		}

		List<Long> result = new ArrayList<>();
		for (String string : split) {
			Long item = Long.valueOf(string);
			result.add(item);
		}

		return result;
	}

	/**
	 * 本地IP地址
	 */
	public static final String LOCAL_IP = "127.0.0.1";
	/**
	 * 默认IP地址
	 */
	public static final String DEFAULT_IP = "0:0:0:0:0:0:0:1";
	/**
	 * 默认IP地址长度
	 */
	public static final int DEFAULT_IP_LENGTH = 15;

	/**
	 * 未知
	 */
	public static final String UNKNOWN_STR = "unknown";
	
	/**
	 * 分隔符
	 */
	public static final String SEPARATOR_STR = ","; 
	
	/**
	 * 获取真实的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIpAddress(HttpServletRequest request) {
		// squid 服务代理
		String ip = request.getHeader("x-forwarded-for");
		
		if (ip == null || ip.length() == 0 || UNKNOWN_STR.equalsIgnoreCase(ip)) {
			// apache服务代理
			ip = request.getHeader("Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0 || UNKNOWN_STR.equalsIgnoreCase(ip)) {
			// weblogic 代理
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || UNKNOWN_STR.equalsIgnoreCase(ip)) {
			// 有些代理
			ip = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ip == null || ip.length() == 0 || UNKNOWN_STR.equalsIgnoreCase(ip)) {
			// nginx代理
			ip = request.getHeader("X-Real-IP"); 
		}

		/*
		 * 如果此时还是获取不到IP地址，那么最后就使用request.getRemoteAddr()来获取
		 */
		if (ip == null || ip.length() == 0 || UNKNOWN_STR.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (LOCAL_IP.equals(ip) || DEFAULT_IP.equals(ip)) {
				// 根据网卡取本机配置的IP
				InetAddress iNet = null;
				try {
					iNet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					Log.error("GetRealIpAddress Error: ", e);
					e.printStackTrace();
				}
				ip = iNet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		// "***.***.***.***".length() = 15
		if (!StringUtils.isEmpty(ip) && ip.length() > DEFAULT_IP_LENGTH) {
			if (ip.indexOf(SEPARATOR_STR) > 0) {
				ip = ip.substring(0, ip.indexOf(SEPARATOR_STR));
			}
		}
		return ip;
	}
}
