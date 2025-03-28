package com.controle.estoque.v1.controller;

import com.controle.estoque.infraSecurity.TokenService;
import com.controle.estoque.model.domain.User;
import com.controle.estoque.repository.UserRepository;
import com.controle.estoque.v1.dto.LoginRequestDTO;
import com.controle.estoque.v1.dto.RegisterDTO;
import com.controle.estoque.v1.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody LoginRequestDTO body){
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(()-> new RuntimeException("User Not Found"));
        if (passwordEncoder.matches(body.password(),user.getPassword())){
            String token = this.tokenService.generationToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(),token));
        }
         return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO body){
        Optional<User> user = userRepository.findByEmail(body.email());
        if(user.isEmpty()){
            User newUser = new User();
            newUser.setEmail(body.email());
            newUser.setNome(body.name());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            this.userRepository.save(newUser);

            String token = this.tokenService.generationToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}
