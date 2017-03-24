package com.momgosample;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by azadr_crhcwau on 3/23/2017.
 */
public class HellowWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "Hello world from Spark";
            }
        });
    }
}
