package com.momgosample;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * Created by azadr_crhcwau on 3/23/2017.
 */
public class AppSparkFreemarkerService {
    public static void main(String[] args) {
        final Configuration freemarkerConfiguration = new Configuration();
        freemarkerConfiguration.setClassForTemplateLoading(AppSparkFreemarkerService.class, "/");
        Spark.get("/getService/:input", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return request.params(":input");
            }
        });

        Spark.get("/getFruits", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Map<String, Object> fruitMap = new HashMap<String, Object>();
                fruitMap.put("fruits", Arrays.asList("apple","banana","citrus","mango"));
                StringWriter writer = new StringWriter();
                try {
                    Template freemarkerTemplate = freemarkerConfiguration.getTemplate("index.ftl");
                    freemarkerTemplate.process(fruitMap, writer);
                    return writer;
                } catch (Exception ex) {
                    halt(500);
                    return null;
                }

            }
        });

        Spark.post("favorite_fruit", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                String fruit = request.queryParams("fruit");
                if (null == fruit) {
                    return "Why don't you pick one from the list?";
                } else {
                    return "Your favorite fruit is " + fruit;
                }
            }
        });
    }
}
