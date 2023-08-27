package net.greeta.movie.vote.cmd.api.commands;

import net.greeta.movie.cqrs.core.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id) {
        super(id);
    }
}
