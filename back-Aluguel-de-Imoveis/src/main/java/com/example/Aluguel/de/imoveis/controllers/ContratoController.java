package com.example.Aluguel.de.imoveis.controllers;


import com.example.Aluguel.de.imoveis.dtos.ContratoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contrato")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ContratoController {
    @PostMapping
    public ResponseEntity criarContrato(@RequestBody ContratoDto contratoDto){
        return ResponseEntity.ok().build();
    }
}
