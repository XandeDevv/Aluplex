package com.example.Aluguel.de.imoveis.controllers;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.dtos.ImovelDto;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.dtos.UserUpdateDto;
import com.example.Aluguel.de.imoveis.repositories.ImovelRepository;
import com.example.Aluguel.de.imoveis.services.AdminService;
import com.example.Aluguel.de.imoveis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/findAllImoveis")
    public ResponseEntity<Page<ImovelDto>> findAllImoveis( Pageable pageable) {
        Page<ImovelDto> imovel= adminService.findAllImoveis(pageable);
        return ResponseEntity.ok().body(imovel);
    }
    @GetMapping("/findAllUsers")
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable){
        Page<UserDto> list= adminService.findAllUsersWithImoveis(pageable);
        return ResponseEntity.ok().body(list);
    }

}
