package com.ucsmy.mc.util;

import okhttp3.OkHttpClient;

/**
 * Created by ucs_mawenzhong on 2017/8/2.
 */
public class OkHttpUtil {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
