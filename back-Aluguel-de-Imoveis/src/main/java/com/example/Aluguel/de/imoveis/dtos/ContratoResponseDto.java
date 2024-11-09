package com.example.Aluguel.de.imoveis.dtos;

import com.example.Aluguel.de.imoveis.domains.Contrato;
import com.example.Aluguel.de.imoveis.domains.ContratoStatus;
import com.example.Aluguel.de.imoveis.domains.User;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public class ContratoResponseDto {

    private Long id;
    private Instant dataDeInicio;
    private Instant dataDeTermino;
    private Duration duracao;
    private BigDecimal valor;
    private ContratoStatus status;
    private UserSQLQuerysDto cliente;
    private UserSQLQuerysDto proprietario;
    public ContratoResponseDto(){
    }
    public ContratoResponseDto(Contrato contrato){
        BeanUtils.copyProperties(contrato,this);
    }

    public ContratoResponseDto(Long id, Instant dataDeInicio, Instant dataDeTermino, Duration duracao, BigDecimal valor, ContratoStatus status, User cliente, User proprietario) {
        this.id = id;
        this.dataDeInicio = dataDeInicio;
        this.dataDeTermino = dataDeTermino;
        this.duracao = duracao;
        this.valor = valor;
        this.status = status;
        this.cliente = new UserSQLQuerysDto(cliente);
        this.proprietario = new UserSQLQuerysDto(proprietario);
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

    public UserSQLQuerysDto getCliente() {
        return cliente;
    }

    public void setCliente(UserSQLQuerysDto cliente) {
        this.cliente = cliente;
    }

    public UserSQLQuerysDto getProprietario() {
        return proprietario;
    }

    public void setProprietario(UserSQLQuerysDto proprietario) {
        this.proprietario = proprietario;
    }
}
