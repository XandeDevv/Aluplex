package com.example.Aluguel.de.imoveis.controllers;


import com.example.Aluguel.de.imoveis.dtos.LoginRequest;
import com.example.Aluguel.de.imoveis.dtos.LoginResponse;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.services.AuthorizationService;

import com.example.Aluguel.de.imoveis.services.exceptions.CustomUnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthenticationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest body) {
        try {
            LoginResponse response = authorizationService.login(body);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais incorretas");
        }
    }

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody @Valid UserInsertDto userInsertDto){
        UserDto newDto= authorizationService.insert(userInsertDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }


}
