package com.picpaychallenge.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaychallenge.dto.UserDto;
import com.picpaychallenge.model.TypeUser;
import com.picpaychallenge.model.User;
import com.picpaychallenge.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransfer(User sender, BigDecimal amount) throws Exception {

        if (sender.getUserType() == TypeUser.LOJISTA) {
            throw new Exception("Lojistas não podem realizar transferencias.");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente.");
        }
    }

    public User findUser(Long id) throws EntityNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public User createUser(UserDto data) {
        User user = new User(data);
        this.saveUser(user);
        return user;
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
