package net.greeta.movie.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddVoteRequest {

    @Schema(example = "10")
    @Positive
    private int score;
}
