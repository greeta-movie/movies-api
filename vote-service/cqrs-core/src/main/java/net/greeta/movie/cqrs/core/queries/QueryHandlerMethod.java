package net.greeta.movie.cqrs.core.queries;

import net.greeta.movie.cqrs.core.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
