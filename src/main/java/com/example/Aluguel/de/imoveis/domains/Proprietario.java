package com.example.Aluguel.de.imoveis.domains;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proprietarios")
public class Proprietario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String email;
    private String cpf;
    @OneToMany(mappedBy = "proprietario")
    private List<Imovel> imoveis= new ArrayList<>();

}
