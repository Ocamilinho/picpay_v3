package com.ocamilinho.picpay_v3.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ocamilinho.picpay_v3.domains.Transaction;
import com.ocamilinho.picpay_v3.domains.UserType;
import com.ocamilinho.picpay_v3.domains.Wallet;
import com.ocamilinho.picpay_v3.domains.DTOs.TransactionDTO;
import com.ocamilinho.picpay_v3.exceptions.InsufficientBalanceException;
import com.ocamilinho.picpay_v3.exceptions.UserTypeCannotMakeTransactionException;
import com.ocamilinho.picpay_v3.repositories.TransactionRepository;

// TODO implementar serviço de notificação com open feign
// TODO implementar serviço de verificação externo
@Service
public class TransactionService {
    final TransactionRepository repository;
    final WalletService walletService;
    final UserService userService;
    public TransactionService(TransactionRepository repository, WalletService walletService, UserService userService) {
        this.repository = repository;
        this.walletService = walletService;
        this.userService = userService;
    }
    
    public TransactionDTO createTransaction(TransactionDTO data){
        Transaction newTransaction = new Transaction();

        Wallet mainWalletSender = walletService.findMainUserWallet(data.sender_id());
        Wallet mainWalletReceiver = walletService.findMainUserWallet(data.receiver_id());

        if(mainWalletSender.getBalance().compareTo(data.amount())<0){
            throw new InsufficientBalanceException();
        }

        if(userService.findUserById(data.sender_id()).getType() == UserType.MERCHANT){
            throw new UserTypeCannotMakeTransactionException(UserType.MERCHANT.toString());
        }

        mainWalletSender.setBalance(mainWalletSender.getBalance().subtract(data.amount()));
        mainWalletReceiver.setBalance(mainWalletReceiver.getBalance().add(data.amount()));

        newTransaction.setSenderWallet(mainWalletSender);
        newTransaction.setReceiverWallet(mainWalletReceiver);
        newTransaction.setTimestamp(LocalDateTime.now());
        newTransaction.setAmount(data.amount());

        repository.save(newTransaction);
        walletService.saveWallet(mainWalletReceiver);
        walletService.saveWallet(mainWalletSender);
        return transferDTO(newTransaction);
    }

    private TransactionDTO transferDTO(Transaction data) {
        return new TransactionDTO(data.getSenderWallet().getId(), data.getReceiverWallet().getId(), data.getAmount());
    }
}
