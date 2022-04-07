package com.crud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.app.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
