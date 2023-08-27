package net.greeta.movie.vote.cmd.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.greeta.movie.cqrs.core.infrastructure.CommandDispatcher;
import net.greeta.movie.vote.cmd.api.commands.OpenAccountCommand;
import net.greeta.movie.vote.cmd.api.commands.UserVoteCommand;
import net.greeta.movie.vote.cmd.api.dto.OpenAccountResponse;
import net.greeta.movie.vote.common.dto.AccountType;
import net.greeta.movie.vote.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OpenAccountService {

    @Autowired
    private CommandDispatcher commandDispatcher;

    public ResponseEntity<BaseResponse> openAccount(OpenAccountCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new OpenAccountResponse("Vote account creation request completed successfully!", command.getId()), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            log.warn(MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to open a new vote account for id - {0}.", command.getId());
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new OpenAccountResponse(safeErrorMessage, command.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void openAccount(String accountHolder, AccountType accountType, int score) {
        val message = new OpenAccountCommand();
        message.setAccountHolder(accountHolder);
        message.setAccountType(accountType);
        message.setOpeningBalance(score);
        val result = openAccount(message);
        if (result.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException(result.getBody().getMessage());
        }
    }


}
