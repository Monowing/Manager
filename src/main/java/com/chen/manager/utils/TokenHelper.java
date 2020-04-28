package com.chen.manager.utils;

import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * Token的帮助类
 * <p>
 * created at 2019-10-31
 *
 * @author MonoWing
 */
public class TokenHelper {

    /**
     * Token第一个参数
     */
    private static final int FIRST_PARAMETER_FOR_TOKEN = 0;

    /**
     * Token生成
     *
     * @param id      ID
     * @param dateStr 时间字符串
     * @return
     */
    public static String generateToken(String id, String dateStr) {
        return JWT.create().withAudience(id).sign(Algorithm.HMAC256(dateStr));
    }

    /**
     * 从Token获取adminId
     *
     * @param token Token值
     * @return adminId的String值
     */
    public static String getStringAdminId(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return JWT.decode(token).getAudience().get(FIRST_PARAMETER_FOR_TOKEN);
    }

    /**
     * 从Token获取adminId
     *
     * @param token Token值
     * @return adminId的Long值
     */
    public static Long getLongAdminId(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String adminId = JWT.decode(token).getAudience()
                .get(FIRST_PARAMETER_FOR_TOKEN);
        if (StringUtils.isEmpty(adminId)) {
            return null;
        }
        return Long.valueOf(adminId);
    }

}
