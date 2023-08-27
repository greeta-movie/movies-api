package net.greeta.movie.client;

import net.greeta.movie.vote.common.dto.MovieScore;
import net.greeta.movie.vote.common.dto.UserBalance;
import net.greeta.movie.vote.common.dto.UserVoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "vote-query")
public interface VoteQueryClient {

    @GetMapping("/movie-score/{imdbId}")
    MovieScore findMovieScoreById(@PathVariable("imdbId") String imdbId);

    @GetMapping("/movie-scores")
    List<MovieScore> findMovieScores();

    @GetMapping("/movie-scores-top")
    List<MovieScore> findTopMovieScores();

    @GetMapping("/user-balance/{username}")
    UserBalance findUserBalanceById(@PathVariable("username") String username);

    @GetMapping("/user-movies")
    List<MovieScore> findUserMovies(@PathVariable("username") String username);

    @GetMapping("/user-votes")
    List<UserVoteDto> findUserVotes(@PathVariable("username") String username);
}
