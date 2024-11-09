package com.example.Aluguel.de.imoveis.repositories;

import com.example.Aluguel.de.imoveis.domains.Imovel;
import com.example.Aluguel.de.imoveis.dtos.ImovelDto;
import com.example.Aluguel.de.imoveis.dtos.ImovelResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel,Long> {
    @Query("SELECT new com.example.Aluguel.de.imoveis.dtos.ImovelResponseDto(i.id, i.name, i.descricao, i.endereco, i.fotos, i.user) " +
            "FROM Imovel i WHERE i.user.email = :email")
    Page<ImovelResponseDto> findImovelByEmail(@Param("email") String email, Pageable pageable);
    @Query("SELECT i FROM Imovel i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Imovel> findImovelByNameContaining(@Param("query") String query, Pageable pageable);

}
