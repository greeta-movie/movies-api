package net.greeta.movie.vote.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.greeta.movie.vote.common.domain.VoteAccount;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLookupResponse extends BaseResponse {
    private List<VoteAccount> accounts;

    public AccountLookupResponse(String message) {
        super(message);
    }
}
