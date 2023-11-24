package com.chslcompany.picpaysimplificado.dtos;

import com.chslcompany.picpaysimplificado.domain.user.UserType;
import com.chslcompany.picpaysimplificado.domain.user.Users;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
