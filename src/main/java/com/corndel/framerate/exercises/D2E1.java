package com.corndel.framerate.exercises;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class D2E1 {

  public static Javalin createApp() {
    var app = Javalin.create(
      config -> {
        var resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/exercises/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");

        var engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        config.fileRenderer(new JavalinThymeleaf(engine));
      });

    app.get(
      "/d2e1",
      ctx -> {
        // Render 'd2e1.html'
        ctx.render("d2e1");
        // Open d2e1.html and follow the instructions
      });

    return app;
  }
}
