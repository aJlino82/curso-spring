package com.crud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.app.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
