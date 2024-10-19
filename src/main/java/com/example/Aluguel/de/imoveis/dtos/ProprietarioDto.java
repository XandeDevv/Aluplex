package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.Proprietario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProprietarioDto {
    private Long Id;
    @NotBlank(message = "required field")
    private String name;
    @Email(message = "invalid email")
    private String email;
    @CPF(message = "send a valid cpf")
    private String cpf;
    private List<ImovelDto> imoveis= new ArrayList<ImovelDto>();
    public ProprietarioDto(){
    };


    public ProprietarioDto(Proprietario proprietario) {
        this.Id = proprietario.getId();
        this.name = proprietario.getName();
        this.email = proprietario.getEmail();
        this.cpf = proprietario.getCpf();
    }
    public ProprietarioDto(Proprietario entity, Set<Imovel> imoveis) {
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
}
