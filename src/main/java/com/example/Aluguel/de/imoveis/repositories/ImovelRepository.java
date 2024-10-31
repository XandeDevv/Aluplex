package com.example.Aluguel.de.imoveis.repositories;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel,Long> {
}
