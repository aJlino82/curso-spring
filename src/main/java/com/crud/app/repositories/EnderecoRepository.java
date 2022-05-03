package com.crud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.app.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
