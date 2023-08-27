package net.greeta.movie.vote.cmd;

import jakarta.annotation.PostConstruct;
import net.greeta.movie.cqrs.core.infrastructure.CommandDispatcher;
import net.greeta.movie.vote.cmd.api.commands.CloseAccountCommand;
import net.greeta.movie.vote.cmd.api.commands.CommandHandler;
import net.greeta.movie.vote.cmd.api.commands.DepositFundsCommand;
import net.greeta.movie.vote.cmd.api.commands.OpenAccountCommand;
import net.greeta.movie.vote.cmd.api.commands.RestoreReadDbCommand;
import net.greeta.movie.vote.cmd.api.commands.WithdrawFundsCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(RestoreReadDbCommand.class, commandHandler::handle);
	}

}
