package com.example.Aluguel.de.imoveis.services.validations;

import java.util.ArrayList;
import java.util.List;


import com.example.Aluguel.de.imoveis.controllers.exceptions.FieldMessage;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;



public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDto> {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDto dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        User user = (User) userRepository.findByEmail(dto.getEmail());

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        if (user !=null){
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