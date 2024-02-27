package com.cherryTest.backendpaycontrol.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderID, Long receiverId) {
}
