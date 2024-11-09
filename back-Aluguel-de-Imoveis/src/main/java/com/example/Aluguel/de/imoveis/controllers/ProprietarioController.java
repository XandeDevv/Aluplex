package com.example.Aluguel.de.imoveis.controllers;

import com.example.Aluguel.de.imoveis.dtos.ImovelDto;
import com.example.Aluguel.de.imoveis.dtos.ImovelResponseDto;
import com.example.Aluguel.de.imoveis.services.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("proprietario")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProprietarioController {
    @Autowired
    ProprietarioService proprietarioService;
    @PostMapping("/addImovel")
    public ResponseEntity addImovel(@RequestBody ImovelDto imovelDto){
        proprietarioService.addImovel(imovelDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Page<ImovelResponseDto>> findAllImoveis(Pageable pageable){
        Page<ImovelResponseDto> list= proprietarioService.findAllImoveis(pageable);
        return ResponseEntity.ok().body(list);
    }
}
