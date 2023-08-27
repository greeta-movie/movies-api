package net.greeta.movie.vote.cmd.api.controllers;

import net.greeta.movie.vote.cmd.api.commands.WithdrawFundsCommand;
import net.greeta.movie.vote.cmd.api.service.WithdrawFundsService;
import net.greeta.movie.vote.common.dto.BaseResponse;
import net.greeta.movie.cqrs.core.exceptions.AggregateNotFoundException;
import net.greeta.movie.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/withdrawFunds")
public class WithdrawFundsController {
    @Autowired
    private WithdrawFundsService withdrawFundsService;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(value = "id") String id,
                                                     @RequestBody WithdrawFundsCommand command) {
        return withdrawFundsService.withdrawFunds(id, command);
    }
}
