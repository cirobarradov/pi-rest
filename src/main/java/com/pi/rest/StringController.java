package com.pi.rest;


import static spark.Spark.*;

import com.pi.core.JsonUtils;
import com.pi.core.StringComparator;
import com.pi.bean.JsonTransformer;
import com.pi.bean.Input;
import spark.Request;
import spark.Response;
import spark.Route;

public class StringController {

    private static StringComparator service = new StringComparator();

    public static void main( String[] args )
    {
        before((request, response) -> response.type("application/json"));
        post("/combine", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                // process request
                Input input = JsonUtils.getLists(request.body());
                String res = service.compareStrings(input.getContent());
                return JsonUtils.formatOutput(res);
            }
        } , new JsonTransformer()) ;
    }
}