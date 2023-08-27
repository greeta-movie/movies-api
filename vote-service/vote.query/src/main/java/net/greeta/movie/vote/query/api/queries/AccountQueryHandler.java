package net.greeta.movie.vote.query.api.queries;

import net.greeta.movie.vote.common.domain.VoteAccount;
import net.greeta.movie.vote.query.api.dto.EqualityType;
import net.greeta.movie.vote.query.domain.AccountRepository;
import net.greeta.movie.cqrs.core.domain.BaseEntity;
import net.greeta.movie.vote.query.domain.UserVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserVoteRepository userVoteRepository;

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        Iterable<VoteAccount> voteAccounts = accountRepository.findAll();
        List<BaseEntity> voteAccountsList = new ArrayList<>();
        voteAccounts.forEach(voteAccountsList::add);
        return voteAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        var voteAccount = accountRepository.findById(query.getId());
        if (voteAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> voteAccountList = new ArrayList<>();
        voteAccountList.add(voteAccount.get());
        return voteAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        var voteAccount = accountRepository.findByAccountHolder(query.getAccountHolder());
        if (voteAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> voteAccountList = new ArrayList<>();
        voteAccountList.add(voteAccount.get());
        return voteAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
        List<BaseEntity> voteAccountsList = query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());
        return voteAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByTypeQuery query) {
        var voteAccounts = accountRepository.findByAccountType(query.getAccountType());
        List<BaseEntity> voteAccountsList = new ArrayList<>();
        voteAccounts.forEach(voteAccountsList::add);
        return voteAccounts;
    }

    @Override
    public List<BaseEntity> handle(FindUserVotesQuery query) {
        var userVotes = userVoteRepository.findByUsername(query.getUsername());
        List<BaseEntity> userVotesList = new ArrayList<>();
        userVotes.forEach(userVotesList::add);
        return userVotesList;
    }

    @Override
    public List<BaseEntity> handle(FindUserVoteQuery query) {
        var userVote = userVoteRepository.findByUsernameAndImdbId(query.getUsername(), query.getImdbId());
        if (userVote.isEmpty()) {
            return null;
        }
        List<BaseEntity> userVotesList = new ArrayList<>();
        userVotesList.add(userVote.get());
        return userVotesList;
    }
}
