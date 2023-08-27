package net.greeta.movie.vote.query.api.controllers;

import net.greeta.movie.vote.common.dto.MovieScore;
import net.greeta.movie.vote.common.dto.UserBalance;
import net.greeta.movie.vote.common.dto.UserVoteDto;
import net.greeta.movie.vote.query.api.service.AccountLookupService;
import net.greeta.movie.vote.query.api.service.VoteQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VoteQueryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteQueryController.class);

    @Autowired
    private VoteQueryService voteQueryService;

    @GetMapping("/movie-score/{imdbId}")
    MovieScore findMovieScoreById(@PathVariable("imdbId") String imdbId) {
        return voteQueryService.findMovieScoreById(imdbId);
    }

    @GetMapping("/movie-scores")
    List<MovieScore> findMovieScores() {
        return voteQueryService.findMovieScores();
    }

    @GetMapping("/movie-scores-top")
    List<MovieScore> findTopMovieScores() {
        return voteQueryService.findTopMovieScores();
    }

    @GetMapping("/user-balance/{username}")
    UserBalance findUserBalanceById(@PathVariable("username") String username) {
        return voteQueryService.findUserBalanceById(username);
    }

    @GetMapping("/user-movies")
    List<MovieScore> findUserMovies(@PathVariable("username") String username) {
        return voteQueryService.findUserMovies(username);
    }

    @GetMapping("/user-votes")
    List<UserVoteDto> findUserVotes(@PathVariable("username") String username) {
        return voteQueryService.findUserVotes(username);
    }

}
