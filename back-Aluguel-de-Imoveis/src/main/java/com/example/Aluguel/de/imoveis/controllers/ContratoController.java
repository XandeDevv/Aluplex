package com.example.Aluguel.de.imoveis.controllers;


import com.example.Aluguel.de.imoveis.dtos.ContratoDto;
import com.example.Aluguel.de.imoveis.services.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contrato")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;
    @PostMapping("/criar")
    public ResponseEntity criarContrato(@RequestBody ContratoDto contratoDto){
        contratoService.criarContrato(contratoDto);
        return ResponseEntity.ok().build();
    }
}
