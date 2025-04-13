package com.picpaychallenge.model;

import java.math.BigDecimal;

import com.picpaychallenge.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String document;
    
    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private TypeUser userType;
    
    public User(UserDto dto){
        this.fullName = dto.fullName();
        this.document = dto.document();
        this.email = dto.email();
        this.password = dto.password();
        this.balance = dto.balance();
        this.userType = dto.userType();
    }
}
