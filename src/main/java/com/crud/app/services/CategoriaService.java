package com.crud.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.crud.app.domain.Categoria;
import com.crud.app.dto.CategoriaDTO;
import com.crud.app.repositories.CategoriaRepository;
import com.crud.app.services.exceptions.DataIntegrityException;
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
		Categoria catObj = findById(categoria.getId());
		updateDataCliente(catObj, categoria);
		return categoriaRepository.save(catObj);
	}

	public void deleteById(Long id) {
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}

	public Categoria saveFromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	private void updateDataCliente(Categoria newCatObj, Categoria objCat) {
		newCatObj.setNome(objCat.getNome());
		 
	}

}
