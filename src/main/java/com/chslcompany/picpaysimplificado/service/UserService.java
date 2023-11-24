package com.chslcompany.picpaysimplificado.service;

import com.chslcompany.picpaysimplificado.domain.user.UserType;
import com.chslcompany.picpaysimplificado.domain.user.Users;
import com.chslcompany.picpaysimplificado.dtos.UserDTO;
import com.chslcompany.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(Users sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Lojistas não estão autorizados a realizar esta transação");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficente");
        }
    }

    public Users findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(Users user){
        this.userRepository.save(user);
    }

    public Users createUser(UserDTO userDTO) {
        Users newUser = new Users(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public List<Users> getAllUsers(){
        return this.userRepository.findAll();
    }
}
