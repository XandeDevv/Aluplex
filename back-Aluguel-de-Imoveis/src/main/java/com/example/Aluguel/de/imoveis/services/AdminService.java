package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.dtos.ImovelDto;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.dtos.UserUpdateDto;
import com.example.Aluguel.de.imoveis.repositories.AdminRepository;
import com.example.Aluguel.de.imoveis.repositories.ImovelRepository;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
import com.example.Aluguel.de.imoveis.services.exceptions.DataBaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @Transactional
    public Page<UserDto> findAllUsersWithImoveis(Pageable pageable) {
        Page<User> page = adminRepository.findAllWithImoveis(pageable);
        return page.map(x -> new UserDto(x));
    }
    @Transactional
    public Page<ImovelDto> findAllImoveis(Pageable pageable) {
        Page<Imovel> page = imovelRepository.findAll(pageable);
        return page.map(x -> new ImovelDto(x));
    }

    public User dtoToEntity(UserDto dto, User obj) {
        obj.setName(dto.getName());
        obj.setEmail(dto.getEmail());
        obj.setCpf(dto.getCpf());
        return obj;
    }
}
