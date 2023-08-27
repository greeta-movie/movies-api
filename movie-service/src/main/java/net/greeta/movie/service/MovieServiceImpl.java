package net.greeta.movie.service;

import lombok.val;
import net.greeta.movie.client.VoteCmdClient;
import net.greeta.movie.client.VoteQueryClient;
import net.greeta.movie.exception.MovieNotFoundException;
import net.greeta.movie.mapper.MovieMapper;
import net.greeta.movie.model.Movie;
import net.greeta.movie.model.UserExtra;
import net.greeta.movie.repository.MovieRepository;
import net.greeta.movie.rest.dto.AddVoteRequest;
import net.greeta.movie.rest.dto.MovieDto;
import net.greeta.movie.vote.common.dto.UserVoteDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final UserExtraService userExtraService;

    private final VoteCmdClient voteCmdClient;

    private final VoteQueryClient voteQueryClient;

    private final MovieMapper movieMapper;

    @Override
    public Movie validateAndGetMovie(String imdbId) {
        return movieRepository.findById(imdbId).orElseThrow(() -> new MovieNotFoundException(imdbId));
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    @Transactional
    public MovieDto addMovieVote(String imdbId, AddVoteRequest addVoteRequest, JwtAuthenticationToken token) {
        Movie movie = validateAndGetMovie(imdbId);
        String username = token.getName();
        Movie.Vote movieVote = new Movie.Vote(username, addVoteRequest.getScore(), LocalDateTime.now());
        movie.getVotes().add(0, movieVote);
        movie = saveMovie(movie);

        UserExtra.Vote userVote = new UserExtra.Vote(username, addVoteRequest.getScore(), LocalDateTime.now());
        Optional<UserExtra> userExtraOptional = userExtraService.getUserExtra(username);
        UserExtra userExtra = userExtraOptional.orElseGet(() -> new UserExtra(username));
        userExtra.getVotes().add(0, userVote);
        userExtraService.saveUserExtra(userExtra);

        val userVoteDto = new UserVoteDto(imdbId, username, addVoteRequest.getScore());
        voteCmdClient.addUserVote(userVoteDto);

        movie.setScoreCqrs(voteQueryClient.findMovieScoreById(movie.getImdbId()).score());
        return movieMapper.toMovieDto(movie);
    }
}