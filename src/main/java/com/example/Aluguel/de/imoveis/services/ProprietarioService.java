package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Proprietario;
import com.example.Aluguel.de.imoveis.dtos.ProprietarioDto;
import com.example.Aluguel.de.imoveis.repositories.ProprietarioRepository;
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
    public Page<ProprietarioDto> findAll(Pageable pageable){
        Page<Proprietario> list= proprietarioRepository.findAll(pageable);
        return list.map(x -> new ProprietarioDto(x));
    }
}
