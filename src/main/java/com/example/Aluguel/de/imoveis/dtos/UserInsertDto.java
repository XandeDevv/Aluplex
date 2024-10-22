package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.services.validations.UserInsertValid;


@UserInsertValid
public class UserInsertDto extends UserDto {
    private String password;
    public UserInsertDto(){
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
