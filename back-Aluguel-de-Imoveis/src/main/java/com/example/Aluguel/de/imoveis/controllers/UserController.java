package com.example.Aluguel.de.imoveis.controllers;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.dtos.UserUpdateDto;
import com.example.Aluguel.de.imoveis.repositories.ImovelRepository;
import com.example.Aluguel.de.imoveis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImovelRepository imovelRepository;
    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable){
        Page<UserDto> list= userService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        UserDto dto= userService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<UserDto> insert(@RequestBody @Valid UserInsertDto dto){
        UserDto newDto= userService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto dto){
        UserDto newDto= userService.update(id,dto);
        return ResponseEntity.ok().body(newDto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/buscar")
    @ResponseBody
    public Page<Imovel> buscarImoveis(@RequestParam("query") String query, Pageable pageable) {
        return imovelRepository.findImovelByNameContaining(query, pageable);
    }
}
