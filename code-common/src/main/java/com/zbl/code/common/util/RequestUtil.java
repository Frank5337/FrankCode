package com.zbl.code.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zbl
 * @Date: Created in 10:41 2019/8/22
 * @Description: 请求工具类
 * @Version: $
 */
public class RequestUtil {

    /**
     * 获取客户端实际IP地址
     */
    public static String getRemoteHost(HttpServletRequest req) {
        if (req == null) {
            return "127.0.0.1";
        }

        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        ip = StringUtil.isBlank(ip) ? ip : ip.split(",")[0];
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取当前请求参数
     */
    public static String  getParams(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();
        StringBuilder paramsBuilder = new StringBuilder();
        boolean first = true;
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (String value : values) {
                if (key != null && !key.contains("password")){
                    if (!first) {
                        paramsBuilder.append("&");
                    }
                    first = false;
                    paramsBuilder.append(key).append("=").append(value);
                }
            }
        }

        return paramsBuilder.toString();
    }

    /**
     * 获取当前请求地址
     */
    public static String getUrl(HttpServletRequest req) {
        return req.getRequestURL().toString();
    }

    /**
     * 获取当前请求方法的类和方法
     */
    public static String[] getClassAndMethod(String regex) {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        Map<String, Object> map = new HashMap<>(16);
        for (int i = 0; i < stack.length; i++) {
            StackTraceElement s = stack[i];
            map.put(("ClassName-" + i), s.getClassName());
            map.put(("MethodName-" + i), s.getMethodName());
        }

        String keyMethod = "";
        String ctrl = "";
        for (Map.Entry<String, Object> m : map.entrySet()) {
            if (m.getValue().toString().matches(regex)) {
                String[] b = m.getValue().toString().split(".*\\.");
                ctrl = b[1].replace(",", "").split("\\$\\$")[0];
                String[] a = m.getKey().split(".*-");
                keyMethod = "MethodName-" + a[1];
            }
        }
        String method = String.valueOf(map.get(keyMethod));
        return new String[]{ctrl, method};
    }
}
