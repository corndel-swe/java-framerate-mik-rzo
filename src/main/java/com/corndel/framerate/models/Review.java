package com.corndel.framerate.models;

public class Review {
  private int id;
  public int movieId;
  public long createdAt;
  public int rating;
  public String content;

  public Review(int id, int movieId, long createdAt, int rating, String content) {
    this.id = id;
    this.movieId = movieId;
    this.createdAt = createdAt;
    this.rating = rating;
    this.content = content;
  }
}
