package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Contrato;
import com.example.Aluguel.de.imoveis.domains.ContratoStatus;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.domains.UserRole;
import com.example.Aluguel.de.imoveis.dtos.ContratoDto;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratoService {

    @Autowired
    private AuthorizationService authorizationService;
    @Transactional
    public ContratoDto criarContrato(ContratoDto contratoDto){
        User user= authorizationService.authenticated();
        Contrato contrato= new Contrato();
        BeanUtils.copyProperties(contratoDto,contrato);
        if(user.hasRole(UserRole.USER)){
            contrato.setCliente(user);
        }if(user.hasRole(UserRole.PROPRIETARIO)){
            contrato.setProprietario(user);
        }else {
            throw new ControllerNotFoundException("Usuario nao habiitado para fazer contratos");
        }
        contrato.setStatus(ContratoStatus.PENDENTE);
        return new ContratoDto(contrato);
    }
    @Transactional
    public void aceitarContrato(ContratoDto contratoDto) {
        User user = authorizationService.authenticated();
        UserDto userDto= new UserDto(user);
        if (contratoDto.getCliente() == null && !user.hasRole(UserRole.USER)) {
            throw new RuntimeException("O usuario deve ser um cliente");
        }
        if (contratoDto.getCliente() == null && user.hasRole(UserRole.USER)) {
            contratoDto.setCliente(userDto);
        }
        if (contratoDto.getProprietario() == null && !user.hasRole(UserRole.PROPRIETARIO)) {
            throw new RuntimeException("O usuario deve ser um proprietario");
        }
        if (contratoDto.getProprietario() == null && user.hasRole(UserRole.PROPRIETARIO)) {
            contratoDto.setProprietario(userDto);
        }
        contratoDto.setStatus(ContratoStatus.EM_ANDAMENTO);
    }
}
