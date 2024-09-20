package com.ocamilinho.picpay_v3.exceptions;

import java.util.UUID;

public class UserNotFoundExpcetion extends RuntimeException {
    
    public UserNotFoundExpcetion(UUID uuid) {
       super("não existe usuário com id:" + uuid);
    }

}
