package com.ocamilinho.picpay_v3.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ocamilinho.picpay_v3.domains.User;
import com.ocamilinho.picpay_v3.domains.DTOs.UserDTO;
import com.ocamilinho.picpay_v3.exceptions.UserNotFoundExpcetion;
import com.ocamilinho.picpay_v3.repositories.UserRepository;
// TODO proteger dados criando um ResponseUserDTO
// TODO refatorar método de criação com carteira vazia
@Service
public class UserService {
    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTO createUser(UserDTO data){
        User newUser = new User(data);
        repository.save(newUser);
        return data;
    }

    public void deleteUser(UUID id) throws Exception{
        if(!repository.existsById(id)){
            throw new UserNotFoundExpcetion(id);
        }
        repository.deleteById(id);
    }

    public List<User> listAllUsers(){
        List<User> list = repository.findAll();
        return list;
    }

    public User findUserById(UUID id){
        return repository.findById(id).orElseThrow(() -> { throw new UserNotFoundExpcetion(id);});
    }

    public UserDTO updateUser(UUID id, UserDTO data){
        User currentUser = repository.findById(id).orElseThrow(() -> { throw new UserNotFoundExpcetion(id);});
        if(data.name() != null){
            currentUser.setName(data.name());
        }
        if(data.email() != null){
            currentUser.setEmail(data.email());
        }
        if(data.password() != null){
            currentUser.setPassword(data.password());
        }
        if(data.document() != null){
            currentUser.setDocument(data.document());
        }
        repository.save(currentUser);
        return transferDTO(currentUser);
    }

    private UserDTO transferDTO(User user) {
        return new UserDTO(user.getName(), user.getDocument(), user.getEmail(), user.getType(), user.getPassword());
    }

    
}
