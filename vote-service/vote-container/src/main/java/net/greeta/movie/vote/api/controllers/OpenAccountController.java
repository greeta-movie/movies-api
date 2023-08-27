package net.greeta.movie.vote.api.controllers;

import net.greeta.movie.vote.api.service.OpenAccountService;
import net.greeta.movie.vote.api.commands.OpenAccountCommand;
import net.greeta.movie.vote.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
