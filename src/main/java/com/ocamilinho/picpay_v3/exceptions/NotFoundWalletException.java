package com.ocamilinho.picpay_v3.exceptions;

import java.util.UUID;

public class NotFoundWalletException extends RuntimeException {
    public NotFoundWalletException(){
        super("não foipossivel encontrar a carteira");
    }
    public NotFoundWalletException(UUID id){
        super("não foi encontrado carteira com o id:" + id);
    }
}
