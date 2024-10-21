package com.example.Aluguel.de.imoveis.services.validations;

import com.example.Aluguel.de.imoveis.controllers.exceptions.FieldMessage;
import com.example.Aluguel.de.imoveis.domains.Proprietario;
import com.example.Aluguel.de.imoveis.dtos.ProprietarioUpdateDto;
import com.example.Aluguel.de.imoveis.repositories.ProprietarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, ProprietarioUpdateDto> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ProprietarioRepository proprietarioRepository;
    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(ProprietarioUpdateDto dto, ConstraintValidatorContext context) {
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();
        Proprietario proprietario = proprietarioRepository.findByEmail(dto.getEmail());

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        if (proprietario!=null && userId!=proprietario.getId()){
            list.add(new FieldMessage("email","email ja existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}