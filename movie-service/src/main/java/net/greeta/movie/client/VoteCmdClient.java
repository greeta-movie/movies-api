package net.greeta.movie.client;

import jakarta.validation.Valid;
import net.greeta.movie.vote.common.dto.UserVoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "vote-cmd")
public interface VoteCmdClient {

    @PostMapping("/user-vote")
    void addUserVote(@Valid @RequestBody UserVoteDto userVote);
}
