package com.cherryTest.backendpaycontrol.dto;

import java.math.BigDecimal;

public record TransactionDTO(Long senderId, Long receiverId, BigDecimal value) {
}
