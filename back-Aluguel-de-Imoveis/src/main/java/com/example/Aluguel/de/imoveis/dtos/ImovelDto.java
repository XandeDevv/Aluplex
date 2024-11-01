package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.User;
import org.springframework.beans.BeanUtils;

public class ImovelDto {
    private Long Id;
    private String descricao;
    private String endereco;
    private String fotos;
    private User user;
    public ImovelDto(){
    };
    public ImovelDto(Imovel imovel){
        BeanUtils.copyProperties(imovel,this);
    }

    public ImovelDto(Long id, String descricao,String endereco, String fotos, User user) {
        Id = id;
        this.descricao = descricao;
        this.fotos = fotos;
        this.user = user;
        this.endereco=endereco;
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
        return user;
    }

    public void setProprietario(User user) {
        this.user = user;
    }
}
