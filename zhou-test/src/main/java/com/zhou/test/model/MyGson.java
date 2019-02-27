package com.zhou.test.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyGson {

    public Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .registerTypeAdapterFactory(new DateNullAdapterFactory<>())
            .create();


    public String toGson(Object src){

        String data =  gson.toJson(src);
        src = new JsonDomain("00");
        return data;
    }

}
