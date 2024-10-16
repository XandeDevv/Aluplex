package com.example.Aluguel.de.imoveis.repositories;

import com.example.Aluguel.de.imoveis.domains.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
