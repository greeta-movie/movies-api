package net.greeta.movie.vote.cmd.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.greeta.movie.cqrs.core.exceptions.AggregateNotFoundException;
import net.greeta.movie.cqrs.core.infrastructure.CommandDispatcher;
import net.greeta.movie.vote.cmd.api.commands.DepositFundsCommand;
import net.greeta.movie.vote.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@Slf4j
@RequiredArgsConstructor
public class DepositFundsService {

    @Autowired
    private CommandDispatcher commandDispatcher;

    public ResponseEntity<BaseResponse> depositFunds(String id, DepositFundsCommand command) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Deposit funds request completed successfully!"), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException e) {
            log.warn(MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to deposit funds to vote account with id - {0}.", id);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void depositFunds(String accountHolder, int score) {
        val message = new DepositFundsCommand();
        message.setAmount(score);
        val result = depositFunds(accountHolder, message);
        if (result.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(result.getBody().getMessage());
        }
    }
}
