package com.crud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.app.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
