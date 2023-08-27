package net.greeta.movie.cqrs.core.producers;

import net.greeta.movie.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
