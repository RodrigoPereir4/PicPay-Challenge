package com.picpaychallenge.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaychallenge.model.Type;
import com.picpaychallenge.model.User;
import com.picpaychallenge.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransfer(User sender, BigDecimal amount) throws Exception {

        if (sender.getUserType() == Type.LOJISTA) {
            throw new Exception("Lojistas não podem realizar transferencias.");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente.");
        }
    }

    public User findUser(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("User not found.");
        }
        return user.get();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
