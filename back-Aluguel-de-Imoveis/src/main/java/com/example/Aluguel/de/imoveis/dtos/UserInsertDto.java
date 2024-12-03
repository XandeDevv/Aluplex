package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.services.validations.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@UserInsertValid
public class UserInsertDto extends UserDto {

    @NotBlank(message = "Required field")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String password;

    private String checkPassword;

    public UserInsertDto(){
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
