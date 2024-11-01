package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Contrato;
import com.example.Aluguel.de.imoveis.domains.ContratoStatus;
import com.example.Aluguel.de.imoveis.domains.User;
import org.springframework.beans.BeanUtils;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public class ContratoDto {

    private Long id;
    private Instant dataDeInicio;
    private Instant dataDeTermino;
    private Duration duracao;
    private BigDecimal valor;
    private ContratoStatus status;
    private UserDto cliente;
    private UserDto proprietario;
    public ContratoDto(){
    }
    public ContratoDto(Contrato contrato){
        BeanUtils.copyProperties(contrato,this);
    }

    public ContratoDto(Long id, Instant dataDeInicio, Instant dataDeTermino, Duration duracao, BigDecimal valor, ContratoStatus status, UserDto cliente, UserDto proprietario) {
        this.id = id;
        this.dataDeInicio = dataDeInicio;
        this.dataDeTermino = dataDeTermino;
        this.duracao = duracao;
        this.valor = valor;
        this.status = status;
        this.cliente = cliente;
        this.proprietario = proprietario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(Instant dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public Instant getDataDeTermino() {
        return dataDeTermino;
    }

    public void setDataDeTermino(Instant dataDeTermino) {
        this.dataDeTermino = dataDeTermino;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ContratoStatus getStatus() {
        return status;
    }

    public void setStatus(ContratoStatus status) {
        this.status = status;
    }

    public UserDto getCliente() {
        return cliente;
    }

    public void setCliente(UserDto cliente) {
        this.cliente = cliente;
    }

    public UserDto getProprietario() {
        return proprietario;
    }

    public void setProprietario(UserDto proprietario) {
        this.proprietario = proprietario;
    }
}
