package com.ocamilinho.picpay_v3.domains.DTOs;
import com.ocamilinho.picpay_v3.domains.UserType;

public record UserDTO(String name, String document, String email, UserType type, String password) {
} 
