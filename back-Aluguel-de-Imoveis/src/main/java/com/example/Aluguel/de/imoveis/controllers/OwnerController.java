package com.example.Aluguel.de.imoveis.controllers;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.dtos.ImovelDto;
import com.example.Aluguel.de.imoveis.dtos.ImovelResponseDto;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("owner")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class OwnerController {
    @Autowired
    OwnerService ownerService;
    @PostMapping("/addImovel")
    public ResponseEntity addImovel(@RequestBody ImovelDto imovelDto){
        ownerService.addImovel(imovelDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Page<ImovelResponseDto>> findAllImoveis(Pageable pageable){
        Page<ImovelResponseDto> list= ownerService.findAllImoveis(pageable);
        return ResponseEntity.ok().body(list);
    }
}
