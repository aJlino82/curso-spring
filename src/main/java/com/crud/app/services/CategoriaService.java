package com.crud.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.app.domain.Categoria;
import com.crud.app.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Optional<Categoria> buscar(Long id) {
		return categoriaRepository.findById(id);
	}

	
}
