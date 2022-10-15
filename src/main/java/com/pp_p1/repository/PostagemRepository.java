package com.pp_p1.repository;

import com.pp_p1.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    @Query("SELECT p FROM Postagem p WHERE p.usuario.codigo = :codigoUsuario")
    List<Postagem> findByCodigoUsuario(Long codigoUsuario);
}
