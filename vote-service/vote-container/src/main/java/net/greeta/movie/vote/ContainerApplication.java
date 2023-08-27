package net.greeta.movie.vote;

import jakarta.annotation.PostConstruct;
import net.greeta.movie.cqrs.core.infrastructure.CommandDispatcher;
import net.greeta.movie.cqrs.core.infrastructure.QueryDispatcher;
import net.greeta.movie.vote.api.commands.CloseAccountCommand;
import net.greeta.movie.vote.api.commands.CommandHandler;
import net.greeta.movie.vote.api.commands.DepositFundsCommand;
import net.greeta.movie.vote.api.commands.OpenAccountCommand;
import net.greeta.movie.vote.api.commands.RestoreReadDbCommand;
import net.greeta.movie.vote.api.commands.WithdrawFundsCommand;
import net.greeta.movie.vote.query.api.queries.FindAccountByHolderQuery;
import net.greeta.movie.vote.query.api.queries.FindAccountByIdQuery;
import net.greeta.movie.vote.query.api.queries.FindAccountWithBalanceQuery;
import net.greeta.movie.vote.query.api.queries.FindAllAccountsQuery;
import net.greeta.movie.vote.query.api.queries.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ContainerApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(ContainerApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(RestoreReadDbCommand.class, commandHandler::handle);

		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handle);
	}

}
