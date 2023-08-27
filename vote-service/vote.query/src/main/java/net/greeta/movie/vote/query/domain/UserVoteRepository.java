package net.greeta.movie.vote.query.domain;

import net.greeta.movie.cqrs.core.domain.BaseEntity;
import net.greeta.movie.vote.common.domain.UserVote;
import net.greeta.movie.vote.common.dto.AccountType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserVoteRepository extends CrudRepository<UserVote, String> {
    List<UserVote> findByUsername(String username);
    List<UserVote> findByImdbId(String imdbId);
    Optional<UserVote> findByUsernameAndImdbId(String username, String imdbId);
}