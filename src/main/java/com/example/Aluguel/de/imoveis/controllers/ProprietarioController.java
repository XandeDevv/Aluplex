package com.example.Aluguel.de.imoveis.controllers;

import com.example.Aluguel.de.imoveis.dtos.ProprietarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/proprietario")
public class ProprietarioController {
    @GetMapping
    public ResponseEntity<Page<ProprietarioDto>> findAll(Pageable pageable){
        return ResponseEntity.ok();
    }
}
