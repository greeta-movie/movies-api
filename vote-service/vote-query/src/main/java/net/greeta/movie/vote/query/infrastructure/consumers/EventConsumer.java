package net.greeta.movie.vote.query.infrastructure.consumers;

import net.greeta.movie.vote.common.events.AccountClosedEvent;
import net.greeta.movie.vote.common.events.AccountOpenedEvent;
import net.greeta.movie.vote.common.events.FundsDepositedEvent;
import net.greeta.movie.vote.common.events.FundsWithdrawnEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);
    void consume(@Payload FundsWithdrawnEvent event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
