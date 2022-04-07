package com.crud.app.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.app.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);

		return lista;
	}

}
