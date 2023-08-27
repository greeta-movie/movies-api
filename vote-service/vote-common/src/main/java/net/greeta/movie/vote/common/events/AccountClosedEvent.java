package net.greeta.movie.vote.common.events;

import net.greeta.movie.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class AccountClosedEvent extends BaseEvent {
}
