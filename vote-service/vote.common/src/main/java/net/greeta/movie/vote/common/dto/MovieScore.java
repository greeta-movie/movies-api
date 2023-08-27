package net.greeta.movie.vote.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record MovieScore (String imdbId, int score) {

}