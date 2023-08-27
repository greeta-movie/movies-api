package net.greeta.movie.vote.api.commands;

import lombok.Builder;
import net.greeta.movie.vote.common.dto.AccountType;
import net.greeta.movie.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}