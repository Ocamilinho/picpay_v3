package com.ocamilinho.picpay_v3.domains.DTOs;

import java.util.UUID;

import com.ocamilinho.picpay_v3.domains.UserType;

public record UserResponseDTO(UUID id, String name, String email, String document, UserType type ) {
} 
