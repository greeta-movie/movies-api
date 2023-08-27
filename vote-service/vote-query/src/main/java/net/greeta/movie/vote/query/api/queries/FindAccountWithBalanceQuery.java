package net.greeta.movie.vote.query.api.queries;

import net.greeta.movie.vote.query.api.dto.EqualityType;
import net.greeta.movie.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
