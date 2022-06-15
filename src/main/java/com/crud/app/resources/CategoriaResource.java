package com.crud.app.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.app.domain.Categoria;
import com.crud.app.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria obj = this.categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public List<Categoria> findAll() {
		return categoriaService.findAll();
	}

	@PostMapping
	public Categoria createCat(@RequestBody Categoria categoria) {
		return categoriaService.save(categoria);
	}

}
