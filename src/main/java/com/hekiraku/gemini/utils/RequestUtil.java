package com.hekiraku.gemini.utils;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.fastjson.util.Base64;
import com.github.pagehelper.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@Slf4j
public class RequestUtil {

    public static final String RETURNURL = "returnUrl";

    private RequestUtil() {
    }

    public static String getString(HttpServletRequest request, String key, String defaultValue, boolean b) {
        String value = request.getParameter(key);
        if (StringUtil.isEmpty(value)) {
            return defaultValue;
        } else {
            return b ? value.replace("'", "''").trim() : value.trim();
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        return getString(request, key, "", true);
    }

    public static String getString(HttpServletRequest request, String key, boolean b) {
        return getString(request, key, "", b);
    }

    public static String getString(HttpServletRequest request, String key, String defaultValue) {
        return getString(request, key, defaultValue, true);
    }

    public static String getStringAry(HttpServletRequest request, String key) {
        String[] aryValue = request.getParameterValues(key);
        if (aryValue != null && aryValue.length != 0) {
            String tmp = "";
            String[] var4 = aryValue;
            int var5 = aryValue.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                String v = var4[var6];
                if ("".equals(tmp)) {
                    tmp = tmp + v;
                } else {
                    tmp = tmp + "," + v;
                }
            }

            return tmp;
        } else {
            return "";
        }
    }

    public static String getSecureString(HttpServletRequest request, String key, String defaultValue) {
        String value = request.getParameter(key);
        return StringUtil.isEmpty(value) ? defaultValue : filterInject(value);
    }

    public static String getSecureString(HttpServletRequest request, String key) {
        return getSecureString(request, key, "");
    }

    public static String filterInject(String str) {
        String injectstr = "\\||;|exec|insert|select|delete|update|count|chr|truncate|char";
        Pattern regex = Pattern.compile(injectstr, 226);
        Matcher matcher = regex.matcher(str);
        str = matcher.replaceAll("");
        str = str.replace("'", "''");
        str = str.replace("-", "—");
        str = str.replace("(", "（");
        str = str.replace(")", "）");
        str = str.replace("%", "％");
        return str;
    }

    public static String getLowercaseString(HttpServletRequest request, String key) {
        return getString(request, key).toLowerCase();
    }

    public static int getInt(HttpServletRequest request, String key) {
        return getInt(request, key, 0);
    }

    public static int getInt(HttpServletRequest request, String key, int defaultValue) {
        String str = request.getParameter(key);
        return StringUtil.isEmpty(str) ? defaultValue : Integer.parseInt(str);
    }

    public static long getLong(HttpServletRequest request, String key) {
        return getLong(request, key, 0L);
    }

    public static Long[] getLongAry(HttpServletRequest request, String key) {
        String[] aryKey = request.getParameterValues(key);
        if (BeanUtils.isEmpty(aryKey)) {
            return null;
        } else {
            Long[] aryLong = new Long[aryKey.length];

            for (int i = 0; i < aryKey.length; ++i) {
                aryLong[i] = Long.parseLong(aryKey[i]);
            }

            return aryLong;
        }
    }

    public static Long[] getLongAryByStr(HttpServletRequest request, String key) {
        String str = request.getParameter(key);
        if (StringUtil.isEmpty(str)) {
            return null;
        } else {
            str = str.replace("[", "");
            str = str.replace("]", "");
            String[] aryId = str.split(",");
            Long[] lAryId = new Long[aryId.length];

            for (int i = 0; i < aryId.length; ++i) {
                lAryId[i] = Long.parseLong(aryId[i]);
            }

            return lAryId;
        }
    }

    public static String[] getStringAryByStr(HttpServletRequest request, String key) {
        String str = request.getParameter(key);
        if (StringUtil.isEmpty(str)) {
            return null;
        } else {
            String[] aryId = str.split(",");
            String[] lAryId = new String[aryId.length];

            for (int i = 0; i < aryId.length; ++i) {
                lAryId[i] = aryId[i];
            }

            return lAryId;
        }
    }

    public static Integer[] getIntAry(HttpServletRequest request, String key) {
        String[] aryKey = request.getParameterValues(key);
        if (BeanUtils.isEmpty(aryKey)) {
            return null;
        } else {
            Integer[] aryInt = new Integer[aryKey.length];

            for (int i = 0; i < aryKey.length; ++i) {
                aryInt[i] = Integer.parseInt(aryKey[i]);
            }

            return aryInt;
        }
    }

    public static Float[] getFloatAry(HttpServletRequest request, String key) {
        String[] aryKey = request.getParameterValues(key);
        if (BeanUtils.isEmpty(aryKey)) {
            return null;
        } else {
            Float[] fAryId = new Float[aryKey.length];

            for (int i = 0; i < aryKey.length; ++i) {
                fAryId[i] = Float.parseFloat(aryKey[i]);
            }

            return fAryId;
        }
    }

    public static long getLong(HttpServletRequest request, String key, long defaultValue) {
        String str = request.getParameter(key);
        return StringUtil.isEmpty(str) ? defaultValue : Long.parseLong(str.trim());
    }

    public static Long getLong(HttpServletRequest request, String key, Long defaultValue) {
        String str = request.getParameter(key);
        return StringUtil.isEmpty(str) ? defaultValue : Long.parseLong(str.trim());
    }

    public static float getFloat(HttpServletRequest request, String key) {
        return getFloat(request, key, 0.0F);
    }

    public static float getFloat(HttpServletRequest request, String key, float defaultValue) {
        String str = request.getParameter(key);
        return StringUtil.isEmpty(str) ? defaultValue : Float.parseFloat(request.getParameter(key));
    }

    public static Short getShort(HttpServletRequest request, String key) {
        return getShort(request, key, Short.valueOf((short) 0));
    }

    public static Short getShort(HttpServletRequest request, String key, Short defaultValue) {
        String str = request.getParameter(key);
        return StringUtil.isEmpty(str) ? defaultValue : Short.parseShort(str);
    }

    public static String getUrl(HttpServletRequest request) {
        StringBuffer urlThisPage = new StringBuffer();
        urlThisPage.append(request.getRequestURI());
        Enumeration<?> e = request.getParameterNames();
        String para = "";
        String values = "";
        urlThisPage.append("?");

        while (e.hasMoreElements()) {
            para = (String) e.nextElement();
            values = request.getParameter(para);
            urlThisPage.append(para);
            urlThisPage.append("=");
            urlThisPage.append(values);
            urlThisPage.append("&");
        }

        return urlThisPage.substring(0, urlThisPage.length() - 1);
    }

    public static String getPrePage(HttpServletRequest request) {
        return StringUtil.isEmpty(request.getParameter("returnUrl"))
                ? request.getHeader("Referer")
                : request.getParameter("returnUrl");
    }

    private static void addQueryParameter(String key, String[] values, Map<String, Object> map) {
        if (values.length == 1) {
            String val = values[0].trim();
            if (StringUtil.isNotEmpty(val)) {
                map.put(key, values[0].trim());
            }
        } else {
            map.put(key, values);
        }

    }

    public static Map getParameterValueMap(HttpServletRequest request, boolean remainArray, boolean isSecure) {
        Map map = new HashMap();
        Enumeration params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String key = params.nextElement().toString();
            String[] values = request.getParameterValues(key);
            if (values != null) {
                String tmpValue;
                if (values.length == 1) {
                    tmpValue = values[0];
                    if (tmpValue != null) {
                        tmpValue = tmpValue.trim();
                        if (!tmpValue.equals("")) {
                            if (isSecure) {
                                tmpValue = filterInject(tmpValue);
                            }

                            if (!tmpValue.equals("")) {
                                map.put(key, tmpValue);
                            }
                        }
                    }
                } else {
                    tmpValue = getByAry(values, isSecure);
                    if (tmpValue.length() > 0) {
                        if (remainArray) {
                            map.put(key, tmpValue.split(","));
                        } else {
                            map.put(key, tmpValue);
                        }
                    }
                }
            }
        }

        return map;
    }

    private static String getByAry(String[] aryTmp, boolean isSecure) {
        String rtn = "";

        for (int i = 0; i < aryTmp.length; ++i) {
            String str = aryTmp[i].trim();
            if (!str.equals("")) {
                if (isSecure) {
                    str = filterInject(str);
                    if (!str.equals("")) {
                        rtn = rtn + str + ",";
                    }
                } else {
                    rtn = rtn + str + ",";
                }
            }
        }

        if (rtn.length() > 0) {
            rtn = rtn.substring(0, rtn.length() - 1);
        }

        return rtn;
    }

    public static String getStringValues(HttpServletRequest request, String paramName) {
        String[] values = request.getParameterValues(paramName);
        if (BeanUtils.isEmpty(values)) {
            return "";
        } else {
            String tmp = "";

            for (int i = 0; i < values.length; ++i) {
                if (i == 0) {
                    tmp = tmp + values[i];
                } else {
                    tmp = tmp + "," + values[i];
                }
            }

            return tmp;
        }
    }

    public static Locale getLocal(HttpServletRequest request) {
        Locale local = request.getLocale();
        if (local == null) {
            local = Locale.CHINA;
        }

        return local;
    }

    public static final String getErrorUrl(HttpServletRequest request) {
        String errorUrl = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (errorUrl == null) {
            errorUrl = (String) request.getAttribute("javax.servlet.forward.request_uri");
        }

        if (errorUrl == null) {
            errorUrl = (String) request.getAttribute("javax.servlet.include.request_uri");
        }

        if (errorUrl == null) {
            errorUrl = request.getRequestURL().toString();
        }

        return errorUrl;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}


