package net.greeta.movie.vote.api.commands;

import lombok.Data;
import net.greeta.movie.cqrs.core.commands.BaseCommand;

@Data
public class UserVoteCommand extends BaseCommand {
    private String imdbId;
    private int score;
}
