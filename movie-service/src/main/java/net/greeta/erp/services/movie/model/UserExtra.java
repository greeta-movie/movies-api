package net.greeta.erp.services.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "userextras")
public class UserExtra {

    @Id
    private String username;
    private String avatar;

    public UserExtra(String username) {
        this.username = username;
    }

    @Data
    @AllArgsConstructor
    public static class Vote {
        private String imdbId;
        private int score;
        private LocalDateTime timestamp;
    }
}
