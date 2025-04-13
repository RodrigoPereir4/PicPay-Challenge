package com.picpaychallenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpaychallenge.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findUserByDocument(String document);
    User findUserById(Long document);
    
}
