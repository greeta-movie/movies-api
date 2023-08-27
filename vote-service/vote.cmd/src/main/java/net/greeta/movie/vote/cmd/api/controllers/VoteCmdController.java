package net.greeta.movie.vote.cmd.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greeta.movie.vote.cmd.api.service.VoteCmdService;
import net.greeta.movie.vote.common.dto.UserVoteDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class VoteCmdController {

    private final VoteCmdService voteCmdService;

    @PostMapping("/user-vote")
    void addUserVote(@Valid @RequestBody UserVoteDto userVote) {
        voteCmdService.addUserVote(userVote);
    }

}