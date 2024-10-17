package com.example.Aluguel.de.imoveis.controllers;

import com.example.Aluguel.de.imoveis.dtos.ProprietarioDto;
import com.example.Aluguel.de.imoveis.services.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/proprietario")
public class ProprietarioController {
    @Autowired
    private ProprietarioService proprietarioService;
    @GetMapping
    public ResponseEntity<Page<ProprietarioDto>> findAll(Pageable pageable){
        Page<ProprietarioDto> list= proprietarioService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProprietarioDto> findById(@PathVariable Long id){
        ProprietarioDto dto= proprietarioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<ProprietarioDto> insert(@RequestBody ProprietarioDto dto){
        dto= proprietarioService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProprietarioDto> update(@PathVariable Long id,@RequestBody ProprietarioDto dto){
        dto= proprietarioService.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }
}
