package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.User;
import org.springframework.beans.BeanUtils;

public class ImovelDto {
    private Long Id;
    private String name;
    private String descricao;
    private String endereco;
    private String fotos;
    private User proprietario;
    public ImovelDto(){
    };
    public ImovelDto(Imovel imovel){
        BeanUtils.copyProperties(imovel,this);
    }

    public ImovelDto(Long id, String name, String descricao,String endereco, String fotos, User proprietario) {
        Id = id;
        this.descricao = descricao;
        this.fotos = fotos;
        this.proprietario = proprietario;
        this.endereco=endereco;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public User getProprietario() {
        return proprietario;
    }

    public void setProprietario(User user) {
        this.proprietario = user;
    }
}
