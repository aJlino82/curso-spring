package com.crud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.app.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
