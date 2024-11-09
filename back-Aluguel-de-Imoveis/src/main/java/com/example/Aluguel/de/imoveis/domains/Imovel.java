package com.example.Aluguel.de.imoveis.domains;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_imoveis")
public class Imovel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String endereco;
    private String descricao;
    private String fotos;
    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private User user;

    public Imovel(){
    }
    public Imovel(Long id,String name,String endereco, String descricao, String fotos, User user) {
        Id = id;
        this.descricao = descricao;
        this.fotos = fotos;
        this.user = user;
        this.endereco=endereco;
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imovel imovel)) return false;
        return Objects.equals(Id, imovel.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public User getProprietario() {
        return user;
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
}
