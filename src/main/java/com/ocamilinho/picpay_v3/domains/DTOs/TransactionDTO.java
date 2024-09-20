package com.ocamilinho.picpay_v3.domains.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(UUID sender_id, UUID receiver_id, BigDecimal amount) {
}