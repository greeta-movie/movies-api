package net.greeta.movie.vote.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.greeta.movie.cqrs.core.infrastructure.CommandDispatcher;
import net.greeta.movie.vote.api.commands.UserVoteCommand;
import net.greeta.movie.vote.api.dto.OpenAccountResponse;
import net.greeta.movie.vote.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserVoteService {

    @Autowired
    private CommandDispatcher commandDispatcher;

    public ResponseEntity<BaseResponse> createUserVote(String id, UserVoteCommand command) {
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new OpenAccountResponse("User Vote creation request completed successfully!", command.getId()), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            log.warn(MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to create a new user vote for id - {0}.", command.getId());
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new OpenAccountResponse(safeErrorMessage, command.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void createUserVote(String accountHolder, String imdbId, int score) {
        val message = new UserVoteCommand();
        message.setImdbId(imdbId);
        message.setScore(score);
        val result = createUserVote(accountHolder, message);
        if (result.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException(result.getBody().getMessage());
        }
    }
}
