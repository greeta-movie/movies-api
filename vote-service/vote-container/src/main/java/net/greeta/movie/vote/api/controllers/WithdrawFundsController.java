package net.greeta.movie.vote.api.controllers;

import net.greeta.movie.vote.api.commands.WithdrawFundsCommand;
import net.greeta.movie.vote.api.service.WithdrawFundsService;
import net.greeta.movie.vote.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
