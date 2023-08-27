package net.greeta.movie.vote.cmd.api.controllers;

import net.greeta.movie.vote.cmd.api.commands.DepositFundsCommand;
import net.greeta.movie.vote.cmd.api.service.DepositFundsService;
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
@RequestMapping(path = "/api/v1/depositFunds")
public class DepositFundsController {
    @Autowired
    private DepositFundsService depositFundsService;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id,
                                                     @RequestBody DepositFundsCommand command) {
        return depositFundsService.depositFunds(id, command);
    }
}
