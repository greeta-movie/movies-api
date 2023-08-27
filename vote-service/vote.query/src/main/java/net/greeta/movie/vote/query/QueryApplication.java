package net.greeta.movie.vote.query;

import jakarta.annotation.PostConstruct;
import net.greeta.movie.cqrs.core.infrastructure.QueryDispatcher;
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
public class QueryApplication {
	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handle);
	}
}
