package com.picpaychallenge.dto;

import java.math.BigDecimal;

import com.picpaychallenge.model.TypeUser;

public record UserDto(String fullName, String document, BigDecimal balance, String email,
                     String password, TypeUser userType) {

}
