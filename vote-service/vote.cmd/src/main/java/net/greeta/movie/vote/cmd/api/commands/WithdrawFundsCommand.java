package net.greeta.movie.vote.cmd.api.commands;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import net.greeta.movie.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class WithdrawFundsCommand extends BaseCommand {
    private double amount;
}