package com.hldj.hmyg.util;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/4/12.
 */

public class GsonUtil<T> {
    private static Gson gson;


    public GsonUtil() {

    }

    public static void getJson() {

    }


    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }

        return gson;
    }


//    public T formateJson2Bean(String json, Class cls) {
//
//
//        return (T) getGson().fromJson(json, cls);
//
//    }

    public static <E> E formateJson2Bean(String json, Class<E> cls) {

//        System.out.println(t.getClass());


        return (E) getGson().fromJson(json, cls);
    }


}
