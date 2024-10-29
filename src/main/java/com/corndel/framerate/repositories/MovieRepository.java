package com.corndel.framerate.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.corndel.framerate.DB;
import com.corndel.framerate.models.Movie;
import com.corndel.framerate.models.Movie.Genre;

public class MovieRepository {
  public static List<Movie> findAll() throws SQLException {
    var query = "SELECT * FROM MOVIES";

    try (var con = DB.getConnection();
        var stmt = con.createStatement();
        var rs = stmt.executeQuery(query);) {

      var movies = new ArrayList<Movie>();
      while (rs.next()) {
        var id = rs.getInt("id");
        var title = rs.getString("title");
        var releaseDate = rs.getString("releaseDate");
        var ageRating = rs.getString("ageRating");
        var runtime = rs.getInt("runtime");
        var imageURL = rs.getString("imageURL");

        String genreString = rs.getString("genre");
        List<Genre> genres = Arrays.stream(genreString.split(","))
            .map(String::trim)
            .map(Genre::valueOf)
            .collect(Collectors.toList());

        movies.add(new Movie(id, title, releaseDate, ageRating, genres, runtime, imageURL));
      }

      return movies;
    }
  }

  public static Movie findById(int id) throws SQLException {
    var query = "SELECT * FROM MOVIES WHERE id = ?";

    try (var con = DB.getConnection();
        var stmt = con.prepareStatement(query)) {

      stmt.setInt(1, id);

      try (var rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        var title = rs.getString("title");
        var releaseDate = rs.getString("releaseDate");
        var ageRating = rs.getString("ageRating");
        var runtime = rs.getInt("runtime");
        var imageURL = rs.getString("imageURL");

        String genreString = rs.getString("genre");
        List<Genre> genres = Arrays.stream(genreString.split(","))
            .map(String::trim)
            .map(Genre::valueOf)
            .collect(Collectors.toList());

        return new Movie(id, title, releaseDate, ageRating, genres, runtime, imageURL);
      }
    }
  }
}
