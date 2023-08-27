package net.greeta.movie.vote.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.greeta.movie.cqrs.core.queries.BaseQuery;
import net.greeta.movie.vote.common.dto.AccountType;

@Data
@AllArgsConstructor
public class FindAccountByTypeQuery extends BaseQuery {
    private AccountType accountType;
}
