package com.pi.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pi.bean.Input;
import com.pi.bean.Output;


public class JsonUtils {

    public static Input getLists (String json)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, Input.class);
    }

    public static String formatOutput (String res)
    {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String result= gson.toJson(new Output(res));
        return result;
    }
}
