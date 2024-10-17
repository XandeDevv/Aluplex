package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Proprietario;
import com.example.Aluguel.de.imoveis.dtos.ProprietarioDto;
import com.example.Aluguel.de.imoveis.repositories.ProprietarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Proprietario obj= proprietarioRepository.getReferenceById(id);
        return  new ProprietarioDto(obj);
    }
    @Transactional
    public ProprietarioDto insert(ProprietarioDto dto) {
        Proprietario obj = new Proprietario();
        BeanUtils.copyProperties(dto,obj);
        obj= proprietarioRepository.save(obj);
        return new ProprietarioDto(obj);
    }
    @Transactional
    public ProprietarioDto update(Long id, ProprietarioDto dto) {
        Proprietario obj= proprietarioRepository.getReferenceById(id);
        BeanUtils.copyProperties(dto,obj,"id");
        obj=proprietarioRepository.save(obj);
        return new ProprietarioDto(obj);
    }

    public void delete(Long id) {
        proprietarioRepository.deleteById(id);
    }
}
