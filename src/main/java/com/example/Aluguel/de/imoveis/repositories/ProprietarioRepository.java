package com.example.Aluguel.de.imoveis.repositories;

import com.example.Aluguel.de.imoveis.domains.Cliente;
import com.example.Aluguel.de.imoveis.domains.Proprietario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario,Long> {
    @Query("SELECT obj FROM Proprietario obj LEFT JOIN FETCH obj.imoveis")
    Page<Proprietario> findAllWithImoveis(Pageable pageable);
}
