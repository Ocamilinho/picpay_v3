package com.ocamilinho.picpay_v3.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ocamilinho.picpay_v3.domains.User;
import com.ocamilinho.picpay_v3.domains.Wallet;
import com.ocamilinho.picpay_v3.domains.DTOs.WalletDTO;
import com.ocamilinho.picpay_v3.exceptions.NotFoundWalletException;
import com.ocamilinho.picpay_v3.exceptions.SingleWalletException;
import com.ocamilinho.picpay_v3.repositories.WalletRepository;
// TODO conferir se a query funciona no banco
// TODO ao apagar carteira principal, definir carteira secundária como principal

@Service
public class WalletService {
    final WalletRepository repository;
    final UserService userService;
    public WalletService(WalletRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }
    public Wallet createWallet(UUID id ,WalletDTO data){
        Wallet newWallet = new Wallet();
        User owner = userService.findUserById(id);
        newWallet.setBalance(BigDecimal.ZERO);
        newWallet.setOwner(owner);
        newWallet.setDescription(data.description());
        newWallet.setIsMain(repository.findWalletsByUserId(id).isEmpty());
        repository.save(newWallet);
        return newWallet;
    }

    public List<Wallet> listUserWallets(UUID id){
         return repository.findWalletsByUserId(id);
    }
    public List<Wallet> listAllWallets() {
        return repository.findAll();
    }
    public Wallet findMainUserWallet(UUID id){
        return repository.findMainUserWallet(id);
    }

    public void removeWallet(UUID id) throws Exception{
        Wallet currentWallet = repository.findById(id).orElseThrow(()->{
            throw new NotFoundWalletException(id);
        });
        UUID owner_id = currentWallet.getOwner().getId();
        
        if(repository.findWalletsByUserId(owner_id).size() == 1){
            throw new SingleWalletException();
        }


        if(!repository.existsById(id)){
            throw new NotFoundWalletException();
        }
        repository.delete(currentWallet);
    }

}
