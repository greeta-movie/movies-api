package net.greeta.movie.cqrs.core.commands;

import lombok.experimental.SuperBuilder;
import net.greeta.movie.cqrs.core.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
