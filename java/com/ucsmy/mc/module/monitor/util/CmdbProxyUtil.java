package com.ucsmy.mc.module.monitor.util;

import com.ucsmy.mc.util.OkHttpUtil;
import com.ucsmy.mc.util.SpringContextUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by ucs_mawenzhong on 2017/5/15.
 */
public class CmdbProxyUtil {
    private static Logger logger = LoggerFactory.getLogger(CmdbProxyUtil.class);

    private static OkHttpClient client = OkHttpUtil.getOkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .build();

    private static String HOST;
    //private static String TOKEN;

    static {
        Properties properties = SpringContextUtil.getPropertiesBean();
        HOST = properties.getProperty("cmdb.host");
        //TOKEN = properties.getProperty("cmdb.token");
    }


    public static String proxyGet(String queryStr) {
        Request request = new Request.Builder()
                .url(HOST + "/itil?" + queryStr)
                .addHeader("Connection", "close")
                .build();
        try {
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return "";
    }

}
