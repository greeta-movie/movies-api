package net.greeta.movie.vote.api.controllers;

import net.greeta.movie.vote.api.commands.DepositFundsCommand;
import net.greeta.movie.vote.api.service.DepositFundsService;
import net.greeta.movie.vote.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
