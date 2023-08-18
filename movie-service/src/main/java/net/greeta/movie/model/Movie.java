package net.greeta.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "movies")
public class Movie {

    @Id
    private String imdbId;
    private String title;
    private String director;
    private String year;
    private String poster;
    private List<Comment> comments = new ArrayList<>();
    private List<Vote> votes = new ArrayList<>();

    @Data
    @AllArgsConstructor
    public static class Comment {
        private String username;
        private String text;
        private LocalDateTime timestamp;
    }

    @Data
    @AllArgsConstructor
    public static class Vote {
        private String username;
        private int score;
        private LocalDateTime timestamp;
    }

    public int getScore() {
        return votes.stream().map(v -> v.getScore()).reduce(0, (i,j) -> i + j);
    }
}