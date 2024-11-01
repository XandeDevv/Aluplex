package com.example.Aluguel.de.imoveis.domains;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_contratos")
public class Contrato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant dataDeInicio;
    private Instant dataDeTermino;
    private Duration duracao;
    private BigDecimal valor;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private User cliente;
    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private User proprietario;
    enum Status {
        DESABILITADO,
        PENDENTE,
        EM_ANDAMENTO;
    }
    public Contrato(){
    }

    public Contrato(Long id,Instant dataDeInicio, Instant dataDeTermino, Duration duracao, BigDecimal valor, Status status) {
        this.id=id;
        this.dataDeInicio = dataDeInicio;
        this.dataDeTermino = dataDeTermino;
        this.duracao = duracao;
        this.valor = valor;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contrato contrato)) return false;
        return Objects.equals(id, contrato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

