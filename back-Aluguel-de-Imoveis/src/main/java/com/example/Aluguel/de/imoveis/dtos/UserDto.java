package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.domains.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDto {
    private Long Id;
    @NotBlank(message = "required field")
    private String name;
    @Email(message = "invalid email")
    private String email;
    @CPF(message = "send a valid cpf")
    private String cpf;
    private UserRole role;
    private List<ImovelDto> imoveis= new ArrayList<ImovelDto>();
    public UserDto(){
    };


    public UserDto(User user) {
        this.Id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.role= user.getRole();
    }
    public UserDto(User entity, Set<Imovel> imoveis) {
        this(entity);
        imoveis.forEach(imo -> this.imoveis.add(new ImovelDto(imo)));
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
