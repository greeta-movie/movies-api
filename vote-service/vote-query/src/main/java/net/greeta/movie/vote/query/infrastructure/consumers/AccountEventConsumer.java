package net.greeta.movie.vote.query.infrastructure.consumers;

import net.greeta.movie.vote.common.events.AccountClosedEvent;
import net.greeta.movie.vote.common.events.AccountOpenedEvent;
import net.greeta.movie.vote.common.events.FundsDepositedEvent;
import net.greeta.movie.vote.common.events.FundsWithdrawnEvent;
import net.greeta.movie.vote.query.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer {
    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AccountOpenedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload FundsDepositedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsWithdrawnEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload FundsWithdrawnEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AccountClosedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }
}
