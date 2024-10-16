package com.example.Aluguel.de.imoveis.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "imoveis")
public class Imovel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String descricao;
    private String fotos;
    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

}
