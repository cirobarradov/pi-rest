package com.pi.core;

import com.google.gson.Gson;
import com.pi.bean.Input;
import com.pi.bean.Output;


public class JsonUtils {

    private static Gson gson = new Gson();
    public static Input getLists (String json)
    {

        return gson.fromJson(json, Input.class);
    }

    public static String formatOutput (String res)
    {
        return gson.toJson(new Output(res));
    }
}
