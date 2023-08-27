package net.greeta.movie.vote.query.domain;

import net.greeta.movie.cqrs.core.domain.BaseEntity;
import net.greeta.movie.vote.common.domain.VoteAccount;
import net.greeta.movie.vote.common.dto.AccountType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<VoteAccount, String> {
    Optional<VoteAccount> findByAccountHolder(String accountHolder);
    List<BaseEntity> findByBalanceGreaterThan(double balance);
    List<BaseEntity> findByBalanceLessThan(double balance);
    List<BaseEntity> findByAccountType(AccountType accountType);
}
