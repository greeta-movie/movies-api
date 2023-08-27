package net.greeta.movie.vote.query.infrastructure.handlers;

import net.greeta.movie.vote.common.events.AccountClosedEvent;
import net.greeta.movie.vote.common.events.AccountOpenedEvent;
import net.greeta.movie.vote.common.events.FundsDepositedEvent;
import net.greeta.movie.vote.common.events.FundsWithdrawnEvent;
import net.greeta.movie.vote.common.events.UserVotedEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
    void on(UserVotedEvent event);
}
