package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Cliente;
import com.example.Aluguel.de.imoveis.domains.Imovel;
import org.springframework.beans.BeanUtils;

public class ClienteDto {
    private Long Id;
    private String name;
    private String email;
    private String cpf;
    public ClienteDto(){
    };
    public ClienteDto(Cliente cliente){
        BeanUtils.copyProperties(cliente,this);
    }
    public ClienteDto(Long id, String name, String email, String cpf) {
        Id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
