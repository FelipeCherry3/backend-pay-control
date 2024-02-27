package com.cherryTest.backendpaycontrol.services;

import com.cherryTest.backendpaycontrol.domain.user.User;
import com.cherryTest.backendpaycontrol.domain.user.UserType;
import com.cherryTest.backendpaycontrol.dto.UserDTO;
import com.cherryTest.backendpaycontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void validarTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() != UserType.COMMON){
            throw new Exception("Usuario Não Autorizado a realizar transação");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }
    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() ->
                new Exception("Usuario não Encontrado"));

    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }
    public void saveUser(User user){
        this.userRepository.save(user);
    }
}

