package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ImovelDto {
    private Long Id;
    private String name;
    private String descricao;
    private BigDecimal preco;
    private String endereco;
    private String proprietarioEmail;
    public ImovelDto(){
    };
    public ImovelDto(Imovel imovel){
        BeanUtils.copyProperties(imovel,this);
    }

    public ImovelDto(Long id, String name, String descricao,BigDecimal preco, String endereco, String proprietarioEmail) {
        Id = id;
        this.descricao = descricao;
        this.preco= preco;
        this.proprietarioEmail = proprietarioEmail;
        this.endereco=endereco;
        this.name=name;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
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

    public String getProprietarioEmail() {
        return proprietarioEmail;
    }

    public void setProprietarioEmail(String proprietarioEmail) {
        this.proprietarioEmail = proprietarioEmail;
    }
}
