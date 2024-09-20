package com.ocamilinho.picpay_v3.exceptions;

public class UserTypeCannotMakeTransactionException extends RuntimeException {
    public UserTypeCannotMakeTransactionException(String type){
        super("o tipo" + type + "não pode realizar transações, apenas receber");
    }
}
