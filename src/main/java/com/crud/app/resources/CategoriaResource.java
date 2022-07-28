package com.crud.app.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.app.domain.Categoria;
import com.crud.app.dto.CategoriaDTO;
import com.crud.app.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> catList = categoriaService.findAll();
		List<CategoriaDTO> catListDTO = catList.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(catListDTO);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria obj = this.categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public Categoria createCat(@RequestBody Categoria categoria) {
		return categoriaService.save(categoria);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria, @PathVariable long id) {
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> deletar(@PathVariable long id) {
		categoriaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> catList = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> catListDTO = catList.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(catListDTO);
	}

}
