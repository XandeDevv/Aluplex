package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.Proprietario;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProprietarioDto {
    private Long Id;
    private String name;
    private String email;
    private String cpf;
    private List<ImovelDto> imoveis= new ArrayList<ImovelDto>();
    public ProprietarioDto(){
    };
    public ProprietarioDto(Proprietario proprietario) {

        BeanUtils.copyProperties(proprietario,this);
    }

    public ProprietarioDto(Long id, String name, String email, String cpf) {
        Id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
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
