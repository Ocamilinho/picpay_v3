package com.ocamilinho.picpay_v3.domains;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonBackReference
    private Wallet senderWallet;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonBackReference
    private Wallet receiverWallet;
    
    private LocalDateTime timestamp;
}
