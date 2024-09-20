package com.ocamilinho.picpay_v3.exceptions;

public class SingleWalletException extends RuntimeException {
    public SingleWalletException(){
        super();
    }
    public SingleWalletException(String message){
        super(message);
    }
}
