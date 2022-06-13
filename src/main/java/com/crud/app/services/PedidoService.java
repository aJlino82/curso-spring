package com.crud.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.app.domain.Pedido;
import com.crud.app.repositories.PedidoRepository;
import com.crud.app.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository clienteRepository;

	public Pedido findById(Long id) {
		Optional<Pedido> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}

	public List<Pedido> findAll() {
		return clienteRepository.findAll();
	}

}
