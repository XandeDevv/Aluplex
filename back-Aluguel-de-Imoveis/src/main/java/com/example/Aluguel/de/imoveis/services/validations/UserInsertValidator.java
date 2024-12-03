package com.example.Aluguel.de.imoveis.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


import com.example.Aluguel.de.imoveis.controllers.exceptions.FieldMessage;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;



public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDto> {

    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserInsertValidator.class);
    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDto dto, ConstraintValidatorContext context) {
        logger.info(dto.getPassword());

        List<FieldMessage> list = new ArrayList<>();
        if (!Pattern.matches(".*[\\W].*", dto.getPassword())) {
            list.add(new FieldMessage("password", "A senha deve possuir pelo menos um caractere especial."));
        }

        if (!Pattern.matches(".*[A-Z].*", dto.getPassword())) {
            list.add(new FieldMessage("password", "A senha deve possuir pelo menos uma letra maiúscula."));
        }

        if (!Pattern.matches(".*[a-z].*", dto.getPassword())) {
            list.add(new FieldMessage("password", "A senha deve possuir pelo menos uma letra minúscula."));
        }

        if (!Pattern.matches(".*[0-9].*", dto.getPassword())) {
            list.add(new FieldMessage("password", "A senha deve possuir pelo menos um número."));
        }
        User user = (User) userRepository.findByEmail(dto.getEmail());

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        if (user !=null){
            list.add(new FieldMessage("email","Email ja existe"));
        }
        if (dto.getCheckPassword() == null || dto.getPassword() == null) {
            list.add(new FieldMessage("checkPassword", "A senha e a confirmação não podem ser nulas"));
        } else if (!dto.getCheckPassword().equals(dto.getPassword())) {
            list.add(new FieldMessage("checkPassword", "As senhas devem ser iguais"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}