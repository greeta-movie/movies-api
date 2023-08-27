package net.greeta.movie.cqrs.core.handlers;

import net.greeta.movie.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);
    void republishEvents();
}
