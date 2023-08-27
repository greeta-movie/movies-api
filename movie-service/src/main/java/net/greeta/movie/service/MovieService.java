package net.greeta.movie.service;

import jakarta.validation.Valid;
import net.greeta.movie.model.Movie;
import net.greeta.movie.rest.dto.AddVoteRequest;
import net.greeta.movie.rest.dto.MovieDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MovieService {

    Movie validateAndGetMovie(String imdbId);

    List<Movie> getMovies();

    Movie saveMovie(Movie movie);

    void deleteMovie(Movie movie);

    MovieDto addMovieVote(String imdbId, AddVoteRequest addVoteRequest, JwtAuthenticationToken token);
}