package com.ocamilinho.picpay_v3.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ocamilinho.picpay_v3.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
}
