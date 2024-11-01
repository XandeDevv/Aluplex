package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.dtos.ImovelDto;
import com.example.Aluguel.de.imoveis.dtos.ImovelResponseDto;
import com.example.Aluguel.de.imoveis.repositories.ImovelRepository;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProprietarioService {
    @Autowired
    ImovelRepository imovelRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorizationService authorizationService;
    @Transactional
    public void addImovel(ImovelDto imovelDto) {
        Imovel imovel = new Imovel();
        imovel = dtoToEntity(imovelDto, imovel);
        User user = authorizationService.authenticated();
        user.addImovel(imovel);
        imovel.setUser(user);
        imovelRepository.save(imovel);
    }
    @Transactional(readOnly = true)
    public Page<ImovelResponseDto> findAllImoveis(Pageable pageable) {
        User user = authorizationService.authenticated();
        String email = user.getEmail();
        return imovelRepository.findImovelByEmail(email, pageable);
    }
    public Imovel dtoToEntity(ImovelDto dto, Imovel obj) {
        obj.setDescricao(dto.getDescricao());
        obj.setEndereco(dto.getEndereco());
        obj.setFotos(dto.getFotos());
        return obj;
    }
}
