package com.cherryTest.backendpaycontrol.dto;

import com.cherryTest.backendpaycontrol.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(Long id, String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
