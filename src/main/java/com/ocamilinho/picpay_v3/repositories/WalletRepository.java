package com.ocamilinho.picpay_v3.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ocamilinho.picpay_v3.domains.Wallet;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID>{
    @Query(nativeQuery = true, value = "SELECT * FROM wallet WHERE owner_id = :id")
    List<Wallet> findWalletsByUserId(@Param("id") UUID id);
    @Query(nativeQuery = true, value = "SELECT * FROM wallet WHERE owner_id = :id AND is_main=1")
    Wallet findMainUserWallet(@Param("id") UUID id);
}
