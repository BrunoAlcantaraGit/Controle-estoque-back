package com.controle.estoque.v1.controller;

import com.controle.estoque.infraSecurity.TokenService;
import com.controle.estoque.model.domain.User;
import com.controle.estoque.repository.UserRepository;
import com.controle.estoque.v1.dto.LoginRequestDTO;
import com.controle.estoque.v1.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final TokenService tokenService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")

    public ResponseEntity Login(LoginRequestDTO body){
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(()-> new RuntimeException("User Not Found"));
        if (passwordEncoder.matches(body.password(),user.getPassword())){
            String token = this.tokenService.generationToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(),token));
        }
         return ResponseEntity.badRequest().build();
    }
}
