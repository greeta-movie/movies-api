package net.greeta.movie.vote.query.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.greeta.movie.cqrs.core.infrastructure.QueryDispatcher;
import net.greeta.movie.vote.common.domain.VoteAccount;
import net.greeta.movie.vote.common.dto.AccountLookupResponse;
import net.greeta.movie.vote.query.api.queries.FindAccountByHolderQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountLookupService {

    private final QueryDispatcher queryDispatcher;

    public ResponseEntity<AccountLookupResponse> getAccountByHolder(@PathVariable(value = "accountHolder") String accountHolder) {
        try {
            List<VoteAccount> accounts = queryDispatcher.send(new FindAccountByHolderQuery(accountHolder));
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully returned movie account!")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get accounts by holder request!";
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public VoteAccount getAccount(String accountHolder) {
        val result = getAccountByHolder(accountHolder);
        if (result != null) {
            return result.getBody().getAccounts().get(0);
        }
        return null;
    }
}
