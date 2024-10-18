package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Proprietario;
import com.example.Aluguel.de.imoveis.dtos.ProprietarioDto;
import com.example.Aluguel.de.imoveis.repositories.ProprietarioRepository;
import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
import com.example.Aluguel.de.imoveis.services.exceptions.DataBaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProprietarioService {
    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Transactional
    public Page<ProprietarioDto> findAll(Pageable pageable) {
        Page<Proprietario> page = proprietarioRepository.findAllWithImoveis(pageable);
        return page.map(x -> new ProprietarioDto(x));
    }

    @Transactional
    public ProprietarioDto findById(Long id) {
        Optional<Proprietario> obj = proprietarioRepository.findById(id);
        Proprietario entity = obj.orElseThrow(() -> new ControllerNotFoundException("Not Found"));
        return new ProprietarioDto(entity);
    }

    @Transactional
    public ProprietarioDto insert(ProprietarioDto dto) {
        Proprietario obj= new Proprietario();
        obj = dtoToEntity(dto,obj);
        obj = proprietarioRepository.save(obj);
        return new ProprietarioDto(obj);
    }

    @Transactional
    public ProprietarioDto update(Long id, ProprietarioDto dto) {
        try {
            Proprietario obj = proprietarioRepository.getReferenceById(id);
            obj= dtoToEntity(dto,obj);
            obj = proprietarioRepository.save(obj);
            return new ProprietarioDto(obj);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Not Found");
        }

    }

    public void delete(Long id) {
        if (!proprietarioRepository.existsById(id)) {
            throw new ControllerNotFoundException("id nao encontrado");
        }
        try {
            proprietarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Cannot delete customer due to existing references");
        }
    }
    public Proprietario dtoToEntity(ProprietarioDto dto,Proprietario obj) {
        obj.setName(dto.getName());
        obj.setEmail(dto.getEmail());
        obj.setCpf(dto.getCpf());
        return obj;
    }
}
