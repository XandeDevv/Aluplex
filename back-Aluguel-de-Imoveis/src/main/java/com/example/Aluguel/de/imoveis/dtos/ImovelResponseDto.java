package com.example.Aluguel.de.imoveis.dtos;

public class ImovelResponseDto {
    private Long id;
    private String descricao;
    private String endereco;
    private String fotos;
    public  ImovelResponseDto(){
    }


    public ImovelResponseDto(Long id, String descricao, String endereco, String fotos) {
        this.id = id;
        this.descricao = descricao;
        this.endereco = endereco;
        this.fotos = fotos;
    }

    public ImovelResponseDto(ImovelResponseDto imovelResponseDto) {
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
