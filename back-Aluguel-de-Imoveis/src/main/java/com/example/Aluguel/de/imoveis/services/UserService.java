package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.dtos.ImovelDto;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.dtos.UserUpdateDto;
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
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private AuthorizationService authorizationService;


    @Transactional
    public Page<ImovelDto> buscar(String name,Pageable pageable) {
        Page<Imovel> page = imovelRepository.findByNameContainingIgnoreCase(name,pageable);
        return page.map(x -> new ImovelDto(x));
    }

    @Transactional
    public UserDto findById(Long id) {
        authorizationService.selfOrAdmin(id);
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ControllerNotFoundException("Not Found"));
        return new UserDto(entity);
    }

    @Transactional
    public UserDto update(Long id, UserUpdateDto dto) {
        try {
            authorizationService.selfOrAdmin(id);
            User obj = userRepository.getReferenceById(id);
            obj= dtoToEntity(dto,obj);
            obj = userRepository.save(obj);
            return new UserDto(obj);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Not Found");
        }

    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ControllerNotFoundException("id nao encontrado");
        }
        try {
            authorizationService.selfOrAdmin(id);
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Cannot delete customer due to existing references");
        }
    }
    public User dtoToEntity(UserDto dto, User obj) {
        obj.setName(dto.getName());
        obj.setEmail(dto.getEmail());
        obj.setCpf(dto.getCpf());
        return obj;
    }
}
