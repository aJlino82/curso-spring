package com.crud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.app.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
