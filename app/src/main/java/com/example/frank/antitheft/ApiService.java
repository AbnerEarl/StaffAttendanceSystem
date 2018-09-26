package com.example.frank.antitheft;

import android.app.Activity;

import com.tamic.novate.Novate;
import com.tamic.novate.callback.RxListCallback;
import com.tamic.novate.callback.RxStringCallback;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class ApiService {
    public static final String Base_Url = URLConfig.ServiceURL;
    private static HashMap<String, Object> header;

    public static void GetString(final Activity activity, final String url, final Map<String, Object> parameter, final RxStringCallback rxStringCallback) {

        header = new HashMap<>();
            header.put("token", "dianming");
            Novate novate = new Novate.Builder(activity)
                    .addCache(false)
                    .baseUrl(Base_Url)
                    .addHeader(header)
                    .addCookie(true)
                    .connectTimeout(500000)
                    .writeTimeout(500000)
                    .build();
            novate.rxPost(url, parameter, rxStringCallback);

    }

    public static void GetList(Activity activity, String url, Map<String, Object> parameter, RxListCallback rxListCallback) {
        header = new HashMap<>();
        header.put("token", "dianming");
        Novate novate = new Novate.Builder(activity)
                .addCache(false)
                .baseUrl(Base_Url)
                .addHeader(header)
                .addCookie(false)
                .build();
        novate.rxPost(url, parameter, rxListCallback);
    }
}