package com.ocamilinho.picpay_v3.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ocamilinho.picpay_v3.exceptions.CannotDeletePrimaryWalletException;
import com.ocamilinho.picpay_v3.exceptions.InsufficientBalanceException;
import com.ocamilinho.picpay_v3.exceptions.NotFoundWalletException;
import com.ocamilinho.picpay_v3.exceptions.SingleWalletException;
import com.ocamilinho.picpay_v3.exceptions.UserNotFoundExpcetion;

@RestControllerAdvice
public class ApplicationAdviceController {
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> insufficientBalance(InsufficientBalanceException ex){
        return new ResponseEntity<>("sem saldo", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundWalletException.class)
    public ResponseEntity<String> notFoundWallet(NotFoundWalletException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SingleWalletException.class)
    public ResponseEntity<String> singleWallet(SingleWalletException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.PRECONDITION_FAILED);
    }
    @ExceptionHandler(UserNotFoundExpcetion.class)
    public ResponseEntity<String> userNotFound(UserNotFoundExpcetion ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CannotDeletePrimaryWalletException.class)
    public ResponseEntity<String> cannotDeletePrimaryWallet(CannotDeletePrimaryWalletException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }
}
