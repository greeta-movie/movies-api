package net.greeta.movie.rest.dto;

import java.time.LocalDateTime;
import java.util.List;

public record UserExtraDto(String username, String avatar,
                       List<VoteDto> votes) {

    public record VoteDto(String imdbId, int score, LocalDateTime timestamp) {
    }
}
