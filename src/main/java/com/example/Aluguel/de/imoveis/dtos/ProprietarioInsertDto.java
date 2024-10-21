package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.services.validations.UserInsertValid;


@UserInsertValid
public class ProprietarioInsertDto extends ProprietarioDto{
    private String password;
    public ProprietarioInsertDto(){
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
