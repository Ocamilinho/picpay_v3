package com.ocamilinho.picpay_v3.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ocamilinho.picpay_v3.domains.User;
import com.ocamilinho.picpay_v3.domains.DTOs.UserDTO;
import com.ocamilinho.picpay_v3.domains.DTOs.UserResponseDTO;
import com.ocamilinho.picpay_v3.exceptions.UserNotFoundExpcetion;
import com.ocamilinho.picpay_v3.repositories.UserRepository;
@Service
public class UserService {
    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDTO createUser(UserDTO data){
        User newUser = new User(data);
        repository.save(newUser);
        return transferDTO(newUser);
    }

    
    public void deleteUser(UUID id) throws Exception{
        if(!repository.existsById(id)){
            throw new UserNotFoundExpcetion(id);
        }
        repository.deleteById(id);
    }

    public List<UserResponseDTO> listAllUsers(){
        List<User> list = repository.findAll();
        return list.stream().map(e->transferDTO(e)).toList();
    }
    
    public User findUserById(UUID id){
        return repository.findById(id).orElseThrow(() -> { throw new UserNotFoundExpcetion(id);});
    }

    public UserResponseDTO updateUser(UUID id, UserDTO data){
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
        if(data.type()!=null){
            currentUser.setType(data.type());
        } 
        repository.save(currentUser);
        return transferDTO(currentUser);
    }
    private UserResponseDTO transferDTO(User data) {
        return new UserResponseDTO(data.getId(), data.getName(), data.getEmail(), data.getDocument(), data.getType());
    }

    
}
