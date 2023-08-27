package net.greeta.movie.vote.cmd.api.dto;

import net.greeta.movie.vote.common.dto.BaseResponse;
import lombok.Data;

@Data
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
