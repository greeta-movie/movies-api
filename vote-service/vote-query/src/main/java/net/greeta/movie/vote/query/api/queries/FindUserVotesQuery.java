package net.greeta.movie.vote.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.greeta.movie.cqrs.core.queries.BaseQuery;

@Data
@AllArgsConstructor
public class FindUserVotesQuery extends BaseQuery {
    private String username;
}

