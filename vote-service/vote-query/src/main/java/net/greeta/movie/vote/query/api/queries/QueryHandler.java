package net.greeta.movie.vote.query.api.queries;

import net.greeta.movie.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllAccountsQuery query);
    List<BaseEntity> handle(FindAccountByIdQuery query);
    List<BaseEntity> handle(FindAccountByHolderQuery query);
    List<BaseEntity> handle(FindAccountWithBalanceQuery query);
    List<BaseEntity> handle(FindAccountByTypeQuery query);
    List<BaseEntity> handle(FindUserVotesQuery query);
    List<BaseEntity> handle(FindUserVoteQuery query);
}
