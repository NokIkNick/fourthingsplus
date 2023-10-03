package app;

import app.config.ThymeleafConfig;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<String, String> userMap = new HashMap<>();

    public static void main(String[] args)
    {

        initUserMap();


        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            JavalinThymeleaf.init(ThymeleafConfig.templateEngine());
        }).start(7070);

        // Routing

        app.get("/", ctx ->  ctx.render("index.html"));
        app.post("/login", ctx -> UserServices.login(ctx,userMap));

    }

    private static void initUserMap() {
        userMap.put("Nicklas","1234");
        userMap.put("Lars","1234");
        userMap.put("Søren","1234");
        userMap.put("Emilie","1234");
    }


}