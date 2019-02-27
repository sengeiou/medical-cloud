package com.zhou.test.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhou.test.model.DateNullAdapterFactory;
import com.zhou.test.model.JsonDomain;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BaseService {


    MyGson gson = new MyGson();

    /**
     * 默认状态   正常返回
     */
    public static String state = "00";

    MyThreadLocal<JsonDomain> jsondomain = new MyThreadLocal<JsonDomain>() {

        @Override
        public JsonDomain initialValue() {
            return new JsonDomain("00");
        }

    };


    public class MyGson {
        public Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapterFactory(new DateNullAdapterFactory<>())
                .create();

        public String toJson(Object src) {
            String data;
            if (src instanceof MyThreadLocal) {
                data = gson.toJson(((MyThreadLocal) src).get());
            } else {
                data = gson.toJson(src);
            }

            return data;
        }

        public <T> T fromJson(String json, Class<T> classOfT) {
            return gson.fromJson(json, classOfT);
        }

        public <T> T fromJson(String json, Type typeOfT) {
            return gson.fromJson(json, typeOfT);
        }

    }

    public class MyThreadLocal<T> extends ThreadLocal {


        public void setDatalist(List<Map<String, Object>> datalist) {
            ((JsonDomain) this.get()).setDatalist(datalist);
        }

        public void setState(String state) {
            ((JsonDomain) this.get()).setState(state);
        }

        public void setTotalnum(int totalnum) {
            ((JsonDomain) this.get()).setTotalnum(totalnum);
        }

    }


}
