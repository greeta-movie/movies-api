package net.greeta.movie.cqrs.core.infrastructure;

import net.greeta.movie.cqrs.core.commands.BaseCommand;
import net.greeta.movie.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
