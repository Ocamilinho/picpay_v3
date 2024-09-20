package com.ocamilinho.picpay_v3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocamilinho.picpay_v3.domains.DTOs.TransactionDTO;
import com.ocamilinho.picpay_v3.services.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("transaction")
public class TransactionController {
    final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO data){
        TransactionDTO response = service.createTransaction(data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
