package net.greeta.movie.vote.cmd.api.controllers;

import net.greeta.movie.vote.cmd.api.commands.OpenAccountCommand;
import net.greeta.movie.vote.cmd.api.dto.OpenAccountResponse;
import net.greeta.movie.vote.cmd.api.service.OpenAccountService;
import net.greeta.movie.vote.common.dto.BaseResponse;
import net.greeta.movie.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/openVoteAccount")
public class OpenAccountController {
    @Autowired
    private OpenAccountService openAccountService;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        return openAccountService.openAccount(command);
    }
}
