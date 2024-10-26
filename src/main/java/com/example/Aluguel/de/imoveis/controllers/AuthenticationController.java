package com.example.Aluguel.de.imoveis.controllers;

import com.example.Aluguel.de.imoveis.dtos.LoginRequest;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.services.AuthorizationService;
import com.example.Aluguel.de.imoveis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthorizationService authorizationService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest){
        var userNamePassword= new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password());
        var auth= this.authenticationManager.authenticate(userNamePassword);
        return ResponseEntity.ok().build();
    }
    @PostMapping("register")
    public ResponseEntity insert(@RequestBody @Valid UserInsertDto userInsertDto){
        UserDto newDto= authorizationService.insert(userInsertDto);
        return ResponseEntity.ok().build();
    }


}
