package com.crud.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.crud.app.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Size(min = 5, max = 80, message = "O minimo é de 5 letras e o máximo é de 80")
	private String nome;

	public CategoriaDTO() {

	}

	public CategoriaDTO(Categoria cat) {
		id = cat.getId();
		nome = cat.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
