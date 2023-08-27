package net.greeta.movie.vote.api.commands;

import lombok.Builder;
import net.greeta.movie.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class DepositFundsCommand extends BaseCommand {
    private double amount;
}
