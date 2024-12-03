package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.User;

import java.math.BigDecimal;

public class ImovelResponseDto {
    private Long id;
    private String name;
    private String descricao;
    private BigDecimal preco;
    private String endereco;
    private String fotos;
    private UserSQLQuerysDto user;
    public  ImovelResponseDto(){
    }


    public ImovelResponseDto(Long id, String name, String descricao, BigDecimal preco, String endereco, String fotos, User user) {
        this.id = id;
        this.name=name;
        this.descricao = descricao;
        this.preco= preco;
        this.endereco = endereco;
        this.fotos = fotos;
        this.user=new UserSQLQuerysDto(user);
    }

    public ImovelResponseDto(ImovelResponseDto imovelResponseDto) {
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

    public UserSQLQuerysDto getUser() {
        return user;
    }

    public void setUser(UserSQLQuerysDto user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }
}
