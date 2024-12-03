package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.domains.UserRole;
import com.example.Aluguel.de.imoveis.dtos.ImovelDto;
import com.example.Aluguel.de.imoveis.dtos.ImovelResponseDto;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.repositories.ImovelRepository;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
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
        if(user.getRole()== UserRole.USER){
            throw new ControllerNotFoundException("arrumar dps");
        }
        user.addImovel(imovel);
        imovel.setUser(user);
        imovelRepository.save(imovel);
    }
    @Transactional(readOnly = true)
    public Page<ImovelDto> findAllImoveis(Pageable pageable) {
        User user = authorizationService.authenticated();
        String email = user.getEmail();
        Page<Imovel> page= imovelRepository.findImovelByEmail(email, pageable);
        return page.map(x -> new ImovelDto(x));
    }
    public Imovel dtoToEntity(ImovelDto dto, Imovel obj) {
        obj.setName(dto.getName());
        obj.setDescricao(dto.getDescricao());
        obj.setPreco(dto.getPreco());
        obj.setEndereco(dto.getEndereco());
        return obj;
    }
}
