package com.ocamilinho.picpay_v3.domains;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    private Wallet sender;

    @ManyToOne
    private Wallet receiver;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;
}
