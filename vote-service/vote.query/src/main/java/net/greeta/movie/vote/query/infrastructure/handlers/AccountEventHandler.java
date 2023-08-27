package net.greeta.movie.vote.query.infrastructure.handlers;

import net.greeta.movie.vote.common.domain.UserVote;
import net.greeta.movie.vote.common.domain.VoteAccount;
import net.greeta.movie.vote.common.events.AccountClosedEvent;
import net.greeta.movie.vote.common.events.AccountOpenedEvent;
import net.greeta.movie.vote.common.events.FundsDepositedEvent;
import net.greeta.movie.vote.common.events.FundsWithdrawnEvent;
import net.greeta.movie.vote.common.events.UserVotedEvent;
import net.greeta.movie.vote.query.domain.AccountRepository;
import net.greeta.movie.vote.query.domain.UserVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountEventHandler implements EventHandler {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserVoteRepository userVoteRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        var voteAccount = VoteAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();
        accountRepository.save(voteAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var voteAccount = accountRepository.findById(event.getId());
        if (voteAccount.isEmpty()) {
            return;
        }
        var currentBalance = voteAccount.get().getBalance();
        var latestBalance = currentBalance + event.getAmount();
        voteAccount.get().setBalance(latestBalance);
        accountRepository.save(voteAccount.get());
    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        var voteAccount = accountRepository.findById(event.getId());
        if (voteAccount.isEmpty()) {
            return;
        }
        var currentBalance = voteAccount.get().getBalance();
        var latestBalance = currentBalance - event.getAmount();
        accountRepository.save(voteAccount.get());
    }

    @Override
    public void on(UserVotedEvent event) {
        var voteAccountOptional = accountRepository.findById(event.getId());
        if (voteAccountOptional.isEmpty()) {
            return;
        }
        var accountHolder = voteAccountOptional.get().getAccountHolder();
        var userVoteOptional = userVoteRepository.findByUsernameAndImdbId(accountHolder, event.getImdbId());
        if (userVoteOptional.isEmpty()) {
            var userVote = UserVote.builder()
                    .id(UUID.randomUUID().toString())
                    .username(accountHolder)
                    .score(event.getScore())
                    .creationDate(event.getCreatedDate())
                    .build();
            userVoteRepository.save(userVote);
        } else {
            var userVote = userVoteOptional.get();
            userVote.setScore(event.getScore());
            userVoteRepository.save(userVote);
        }
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
