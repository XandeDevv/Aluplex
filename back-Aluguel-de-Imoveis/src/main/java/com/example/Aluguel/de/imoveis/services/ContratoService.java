package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.Contrato;
import com.example.Aluguel.de.imoveis.domains.ContratoStatus;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.domains.UserRole;
import com.example.Aluguel.de.imoveis.dtos.ContratoDto;
import com.example.Aluguel.de.imoveis.dtos.ContratoResponseDto;
import com.example.Aluguel.de.imoveis.dtos.ImovelResponseDto;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.repositories.ContratoRepository;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizationService authorizationService;
    @Transactional
    public ContratoDto criarContrato(ContratoDto contratoDto) {
        User user = authorizationService.authenticated();
        UserDto userDto = new UserDto(user);

        if (user.hasRole(UserRole.USER)) {
            contratoDto.setCliente(userDto);
        } else if (user.hasRole(UserRole.PROPRIETARIO)) {
            contratoDto.setProprietario(userDto);
        } else {
            throw new ControllerNotFoundException("Usuário não habilitado para fazer contratos: " + user.getRole());
        }

        contratoDto.setStatus(ContratoStatus.PENDENTE);
        Contrato contrato = converterContratoDtoParaContrato(contratoDto);
        contrato = contratoRepository.save(contrato);

        return new ContratoDto(contrato);
    }

    @Transactional
    public ContratoDto aceitarContrato(ContratoDto contratoDto) {
        User user = authorizationService.authenticated();
        UserDto userDto = new UserDto(user);

        if (contratoDto.getCliente() == null && user.hasRole(UserRole.USER)) {
            contratoDto.setCliente(userDto);
        } else if (contratoDto.getProprietario() == null && user.hasRole(UserRole.PROPRIETARIO)) {
            contratoDto.setProprietario(userDto);
        }
        if (contratoDto.getCliente() == null || contratoDto.getProprietario() == null) {
            throw new ControllerNotFoundException("Usuário não habilitado para fazer contratos");
        }

        contratoDto.setStatus(ContratoStatus.EM_ANDAMENTO);
        Contrato contrato = converterContratoDtoParaContrato(contratoDto);
        contrato.setValor(new BigDecimal(32.32));
        contrato = contratoRepository.save(contrato);

        return new ContratoDto(contrato);
    }
    @Transactional(readOnly = true)
    public Page<ContratoResponseDto> findAllContracts(Pageable pageable) {
        User user = authorizationService.authenticated();
        String email = user.getEmail();
        return contratoRepository.findContratoByEmail(email, pageable);
    }
    private Contrato converterContratoDtoParaContrato(ContratoDto contratoDto) {
        Contrato contrato;
        if(contratoDto.getId()==null){
            contrato= new Contrato();
        }else {contrato= contratoRepository.findById(contratoDto.getId()).orElseThrow(()->new ControllerNotFoundException("O contrato noa existe"));
        }

        BeanUtils.copyProperties(contratoDto, contrato);

        if (contratoDto.getCliente() != null) {
            contrato.setCliente(userRepository.findById(contratoDto.getCliente().getId())
                    .orElseThrow(() -> new ControllerNotFoundException("Cliente não encontrado")));
        }
        if (contratoDto.getProprietario() != null) {
            contrato.setProprietario(userRepository.findById(contratoDto.getProprietario().getId())
                    .orElseThrow(() -> new ControllerNotFoundException("Proprietário não encontrado")));
        }

        return contrato;
    }


}
