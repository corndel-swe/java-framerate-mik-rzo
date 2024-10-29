package com.corndel.framerate.models;

import java.util.List;

public class Movie {
  public enum Genre {
    Adventure,
    Action,
    Animation,
    Biography,
    Comedy,
    Crime,
    Drama,
    Family,
    Fantasy,
    History,
    Horror,
    Mystery,
    Romance,
    SciFi,
    Thriller,
    War
  }

  public Movie() {
  }

  public Movie(
    int id,
    String title,
    String releaseDate,
    String ageRating,
    List<Genre> genres,
    int runtime,
    String imageURL) {
    this.id = id;
    this.title = title;
    this.releaseDate = releaseDate;
    this.ageRating = ageRating;
    this.genres = genres;
    this.runtime = runtime;
    this.imageURL = imageURL;
  }

  private int id;
  public String title;
  public String releaseDate;
  public String ageRating;
  public List<Genre> genres;
  public int runtime;
  public String imageURL;

  public String getGenresAsString() {
    return String.join(", ", this.genres.stream().map(Movie.Genre::name).toArray(String[]::new));
  }

  public String getTitle() {
    return this.title;
  }
}
