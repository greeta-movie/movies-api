package net.greeta.movie.vote.query.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greeta.movie.cqrs.core.infrastructure.QueryDispatcher;
import net.greeta.movie.vote.common.domain.UserVote;
import net.greeta.movie.vote.common.domain.VoteAccount;
import net.greeta.movie.vote.common.dto.AccountType;
import net.greeta.movie.vote.common.dto.MovieScore;
import net.greeta.movie.vote.common.dto.UserBalance;
import net.greeta.movie.vote.common.dto.UserVoteDto;
import net.greeta.movie.vote.query.api.queries.FindAccountByHolderQuery;
import net.greeta.movie.vote.query.api.queries.FindAccountByTypeQuery;
import net.greeta.movie.vote.query.api.queries.FindUserVotesQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class VoteQueryService {

    private final QueryDispatcher queryDispatcher;

    public MovieScore findMovieScoreById(String imdbId) {
        List<VoteAccount> accounts = queryDispatcher.send(new FindAccountByHolderQuery(imdbId));
        if (CollectionUtils.isNotEmpty(accounts)) {
            return new MovieScore(imdbId, (int) accounts.get(0).getBalance());
        }
        return null;
    }

    public List<MovieScore> findMovieScores() {
        List<VoteAccount> accounts = queryDispatcher.send(new FindAccountByTypeQuery(AccountType.MOVIE));
        if (CollectionUtils.isNotEmpty(accounts)) {
            return accounts.stream().map(va -> new MovieScore(va.getAccountHolder(), (int) va.getBalance())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<MovieScore> findTopMovieScores() {
        List<VoteAccount> accounts = queryDispatcher.send(new FindAccountByTypeQuery(AccountType.MOVIE));
        if (CollectionUtils.isNotEmpty(accounts)) {
            return accounts.stream().map(va -> new MovieScore(va.getAccountHolder(), (int) va.getBalance())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public UserBalance findUserBalanceById(String username) {
        List<VoteAccount> accounts = queryDispatcher.send(new FindAccountByTypeQuery(AccountType.VOTER));
        if (CollectionUtils.isNotEmpty(accounts)) {
            return new UserBalance(username, (int) accounts.get(0).getBalance());
        }
        return null;
    }

    public List<MovieScore> findUserMovies(String username) {
        List<UserVote> userVotes = queryDispatcher.send(new FindUserVotesQuery(username));
        if (CollectionUtils.isNotEmpty(userVotes)) {
            return userVotes.stream().map(uv -> new MovieScore(uv.getImdbId(), uv.getScore())).collect(Collectors.toList());
        }
        return null;
    }

    public List<UserVoteDto> findUserVotes(String username) {
        List<UserVote> userVotes = queryDispatcher.send(new FindUserVotesQuery(username));
        if (CollectionUtils.isNotEmpty(userVotes)) {
            return userVotes.stream().map(uv -> new UserVoteDto(uv.getImdbId(), username, uv.getScore())).collect(Collectors.toList());
        }
        return null;
    }
}
