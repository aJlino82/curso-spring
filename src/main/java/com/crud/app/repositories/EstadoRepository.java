package com.crud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.app.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
