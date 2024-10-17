package com.example.Aluguel.de.imoveis.domains;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_imovel")
public class Imovel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String endereco;
    private String descricao;
    private String fotos;
    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    public Imovel(){
    };

    public Imovel(Long id,String endereco, String descricao, String fotos, Proprietario proprietario) {
        Id = id;
        this.descricao = descricao;
        this.fotos = fotos;
        this.proprietario = proprietario;
        this.endereco=endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Proprietario getProprietario() {
        return proprietario;
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
