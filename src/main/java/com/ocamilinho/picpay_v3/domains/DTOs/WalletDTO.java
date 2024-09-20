package com.ocamilinho.picpay_v3.domains.DTOs;

import java.math.BigDecimal;

public record WalletDTO(String description, BigDecimal balance) {
} 
