package com.controle.estoque.service;

import com.controle.estoque.model.domain.User;
import com.controle.estoque.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> findUser(String email) throws Exception{
        Optional<User>user = userRepository.findByEmail(email);
        if (!user.isEmpty()){
            return userRepository.findByEmail(email);
        }else {
            throw new RuntimeException("User not a found");
        }

    }
}
