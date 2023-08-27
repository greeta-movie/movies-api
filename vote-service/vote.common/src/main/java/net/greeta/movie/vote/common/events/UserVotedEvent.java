package net.greeta.movie.vote.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.greeta.movie.cqrs.core.events.BaseEvent;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserVotedEvent extends BaseEvent {
    private String imdbId;
    private int score;
    private Date createdDate;
}

