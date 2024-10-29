package com.corndel.framerate;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.corndel.framerate.models.Movie;
import com.corndel.framerate.models.Review;
import com.corndel.framerate.repositories.MovieRepository;
import com.corndel.framerate.repositories.ReviewRepository;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) {
    var javalin = createApp();
    javalin.start(8081);
  }

  public static Javalin createApp() {
    var app = Javalin.create(
      config -> {
        config.staticFiles.add("/public", Location.CLASSPATH);

        var resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");

        var engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        config.fileRenderer(new JavalinThymeleaf(engine));
      });

    app.get("/", ctx -> {
      ctx.render("/movie/index", Map.of("movies", MovieRepository.findAll()));
    });

    app.get("/movie/{id}", ctx -> {
      int movieId = Integer.parseInt(ctx.pathParam("id"));
      Movie movie = MovieRepository.findById(movieId);
      List<Review> reviews = ReviewRepository.findByMovie(movieId);
      ctx.render("/movie/single", Map.of("movie", movie, "reviews", reviews));
    });

    return app;
  }
}
