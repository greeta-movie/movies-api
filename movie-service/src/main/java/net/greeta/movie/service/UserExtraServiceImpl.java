package net.greeta.movie.service;

import lombok.val;
import net.greeta.movie.client.VoteQueryClient;
import net.greeta.movie.exception.UserExtraNotFoundException;
import net.greeta.movie.model.UserExtra;
import net.greeta.movie.repository.UserExtraRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserExtraServiceImpl implements UserExtraService {

    private final UserExtraRepository userExtraRepository;

    private final VoteQueryClient voteQueryClient;

    @Override
    public UserExtra validateAndGetUserExtra(String username) {
        val result = getUserExtra(username).orElseThrow(() -> new UserExtraNotFoundException(username));
        result.setBalance(voteQueryClient.findUserBalanceById(username).score());
        return result;
    }

    @Override
    public Optional<UserExtra> getUserExtra(String username) {
        return userExtraRepository.findById(username);
    }

    @Override
    public UserExtra saveUserExtra(UserExtra userExtra) {
        return userExtraRepository.save(userExtra);
    }
}
