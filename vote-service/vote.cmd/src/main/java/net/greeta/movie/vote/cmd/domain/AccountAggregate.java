package net.greeta.movie.vote.cmd.domain;

import net.greeta.movie.vote.cmd.api.commands.OpenAccountCommand;
import net.greeta.movie.vote.common.events.AccountClosedEvent;
import net.greeta.movie.vote.common.events.AccountOpenedEvent;
import net.greeta.movie.vote.common.events.FundsDepositedEvent;
import net.greeta.movie.vote.common.events.FundsWithdrawnEvent;
import net.greeta.movie.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;
import net.greeta.movie.vote.common.events.UserVotedEvent;

import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    private double balance;

    public double getBalance() {
        return this.balance;
    }

    public Boolean getActive() {
        return this.active;
    }

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder()
                    .id(command.getId())
                    .accountHolder(command.getAccountHolder())
                    .createdDate(new Date())
                    .accountType(command.getAccountType())
                    .openingBalance(command.getOpeningBalance())
                    .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Funds cannot be deposited into a closed account!");
        }
        if(amount <= 0) {
            throw new IllegalStateException("The deposit amount must be greater than 0!");
        }
        raiseEvent(FundsDepositedEvent.builder()
                    .id(this.id)
                    .amount(amount)
                    .build());
    }

    public void apply(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Funds cannot be withdrawn from a closed account!");
        }
        raiseEvent(FundsWithdrawnEvent.builder()
                    .id(this.id)
                    .amount(amount)
                    .build());
    }

    public void userVote(String imdbId, int score) {
        if (!this.active) {
            throw new IllegalStateException("User Vote cannot be registered for a closed account!");
        }
        raiseEvent(UserVotedEvent.builder()
                .id(this.id)
                .imdbId(imdbId)
                .score(score)
                .createdDate(new Date())
                .build());
    }

    public void apply(FundsWithdrawnEvent event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount() {
        if (!this.active) {
            throw new IllegalStateException("The vote account has already been closed!");
        }
        raiseEvent(AccountClosedEvent.builder()
                    .id(this.id)
                    .build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
