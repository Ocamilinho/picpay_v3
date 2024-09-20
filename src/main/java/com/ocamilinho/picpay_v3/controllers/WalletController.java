package com.ocamilinho.picpay_v3.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocamilinho.picpay_v3.domains.Wallet;
import com.ocamilinho.picpay_v3.domains.DTOs.WalletDTO;
import com.ocamilinho.picpay_v3.services.WalletService;

@RestController
@RequestMapping("wallet")
public class WalletController {
    final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }
    @PostMapping("{id}")
    public ResponseEntity<Wallet> createWallet(@PathVariable String id, @RequestBody WalletDTO data){
        Wallet newWallet = service.createWallet(UUID.fromString(id), data);
        return new ResponseEntity<>(newWallet, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable String id) throws Exception{
        service.removeWallet(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<List<Wallet>> listWalletsByUser(@PathVariable String id){
        List<Wallet> list = service.listUserWallets(UUID.fromString(id));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("{id}/main")
    public ResponseEntity<Wallet> findMainUserWallet(@PathVariable String id){
        Wallet newWallet = service.findMainUserWallet(UUID.fromString(id));
        return new ResponseEntity<>(newWallet, HttpStatus.CREATED);
    }

}
