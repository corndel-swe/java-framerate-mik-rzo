package com.corndel.framerate.repositories;

import com.corndel.framerate.DB;
import com.corndel.framerate.models.Review;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
  public static List<Review> findByMovie(int movieId) throws SQLException {
    var query = "SELECT * FROM REVIEWS WHERE movieId = ?";

    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query)) {
      stmt.setInt(1, movieId);
      try (var rs = stmt.executeQuery()) {
        var reviews = new ArrayList<Review>();
        while (rs.next()) {
          int id = rs.getInt("id");
          long createdAt = rs.getTimestamp("createdAt").getTime();
          int rating = rs.getInt("rating");
          String content = rs.getString("content");

          reviews.add(new Review(id, movieId, createdAt, rating, content));
        }
        return reviews;
      }
    }
  }

  public static void insertReview(int movieId, int rating, String content) throws SQLException {
    String query = "INSERT INTO reviews (movieId, rating, content) VALUES (?, ?, ?)";

    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query)) {
      stmt.setInt(1, movieId);
      stmt.setInt(2, rating);
      stmt.setString(3, content);
      boolean result = stmt.executeUpdate() == 1;
      if (!result) {
        throw new SQLException("Insertion failed: No rows affected.");
      }
    }
  }
}
