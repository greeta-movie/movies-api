package net.greeta.movie.cqrs.core.infrastructure;

import net.greeta.movie.cqrs.core.domain.BaseEntity;
import net.greeta.movie.cqrs.core.queries.BaseQuery;
import net.greeta.movie.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
