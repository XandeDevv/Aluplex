package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.domains.UserRole;

public class UserSQLQuerysDto {
    private Long Id;
    private String name;
    private String email;
    private String cpf;
    private UserRole role;

    public UserSQLQuerysDto(){
    };


    public UserSQLQuerysDto(User user) {
        this.Id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.role= user.getRole();
    }

    public UserSQLQuerysDto(Long id, String name, String email, String cpf, UserRole role) {
        Id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
