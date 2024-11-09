package com.example.Aluguel.de.imoveis.repositories;

import com.example.Aluguel.de.imoveis.domains.Contrato;
import com.example.Aluguel.de.imoveis.dtos.ContratoResponseDto;
import com.example.Aluguel.de.imoveis.dtos.ImovelResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato,Long> {
    @Query(value = "SELECT i.id, i.data_de_inicio, i.data_de_termino, i.duracao, i.valor, i.status, i.cliente_id, i.proprietario_id " +
            "FROM tb_contratos i " +
            "LEFT JOIN tb_users cliente ON i.cliente_id = cliente.id " +
            "LEFT JOIN tb_users proprietario ON i.proprietario_id = proprietario.id " +
            "WHERE cliente.email = :email OR proprietario.email = :email", nativeQuery = true)
    Page<ContratoResponseDto> findContratoByEmail(@Param("email") String email, Pageable pageable);
}