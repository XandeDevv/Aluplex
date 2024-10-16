package com.example.Aluguel.de.imoveis.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "proprietarios")
public class Proprietario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String email;
    private String cpf;

}
