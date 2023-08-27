package net.greeta.movie.vote.api.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greeta.movie.vote.api.client.VoteQueryClient;
import net.greeta.movie.vote.common.dto.AccountLookupResponse;
import net.greeta.movie.vote.common.dto.AccountType;
import net.greeta.movie.vote.common.dto.UserVoteDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@Slf4j
@RequiredArgsConstructor
public class VoteCmdService {

    public static int USER_INIT_SCORE = 100;

    private final VoteQueryClient voteQueryClient;
    private final OpenAccountService openAccountService;
    private final DepositFundsService depositFundsService;
    private final WithdrawFundsService withdrawFundsService;
    private final UserVoteService userVoteService;

    public void addUserVote(@Valid @RequestBody UserVoteDto userVote) {
        AccountLookupResponse userAccount = voteQueryClient.getAccountByHolder(userVote.username());
        if (userAccount == null) {
            if (USER_INIT_SCORE - userVote.score() < 0) {
                throw new RuntimeException("Not Enough Balance For User Account");
            }
            openAccountService.openAccount(userVote.username(), AccountType.VOTER, USER_INIT_SCORE - userVote.score());
        } else {
            if (userAccount.getAccounts().get(0).getBalance() - userVote.score() < 0) {
                throw new RuntimeException("Not Enough Balance For User Account");
            }
            withdrawFundsService.withdrawFunds(userVote.username(), userVote.score());
        }
        AccountLookupResponse movieAccount = voteQueryClient.getAccountByHolder(userVote.imdbId());
        if (movieAccount == null) {
            openAccountService.openAccount(userVote.imdbId(), AccountType.MOVIE, userVote.score());
        } else {
            depositFundsService.depositFunds(userVote.imdbId(), userVote.score());
        }
        userVoteService.createUserVote(userVote.username(), userVote.imdbId(), userVote.score());
    }

}
