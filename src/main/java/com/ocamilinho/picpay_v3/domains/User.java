package com.ocamilinho.picpay_v3.domains;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ocamilinho.picpay_v3.domains.DTOs.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    public User(UserDTO data) {
        this.name = data.name();
        this.document = data.document();
        this.email = data.email();
        this.type = data.type();
        this.password = data.password();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String document;
    private String email;
    private String password;
    private UserType type;
    @JsonManagedReference
    @OneToMany(mappedBy = "owner")
    private List<Wallet> wallets;
}
