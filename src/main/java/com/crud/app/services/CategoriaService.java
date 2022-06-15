package com.crud.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.app.domain.Categoria;
import com.crud.app.repositories.CategoriaRepository;
import com.crud.app.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria findById(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public <S extends Categoria> S save(S entity) {
		return categoriaRepository.save(entity);
	}

	public Categoria update(Categoria categoria) {
		findById(categoria.getId());
		return categoriaRepository.save(categoria);
	}

}
