package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.domains.Proprietario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.BeanUtils;

public class ImovelDto {
    private Long Id;
    private String descricao;
    private String fotos;
    private Proprietario proprietario;
    public ImovelDto(){
    };
    public ImovelDto(Imovel imovel){
        BeanUtils.copyProperties(imovel,this);
    }

    public ImovelDto(Long id, String descricao, String fotos, Proprietario proprietario) {
        Id = id;
        this.descricao = descricao;
        this.fotos = fotos;
        this.proprietario = proprietario;
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

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
