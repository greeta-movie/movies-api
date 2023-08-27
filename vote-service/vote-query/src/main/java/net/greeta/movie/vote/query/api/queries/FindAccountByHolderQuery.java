package net.greeta.movie.vote.query.api.queries;

import net.greeta.movie.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByHolderQuery extends BaseQuery {
    private String accountHolder;
}
