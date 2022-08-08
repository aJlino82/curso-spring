package com.crud.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.app.domain.Cidade;
import com.crud.app.domain.Cliente;
import com.crud.app.domain.Endereco;
import com.crud.app.domain.enums.TipoCliente;
import com.crud.app.dto.ClienteDTO;
import com.crud.app.dto.ClienteNewDTO;
import com.crud.app.repositories.ClienteRepository;
import com.crud.app.repositories.EnderecoRepository;
import com.crud.app.services.exceptions.DataIntegrityException;
import com.crud.app.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;


	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Transactional
	public <Scli extends Cliente> Scli save(Scli entity) {
		enderecoRepository.saveAll(entity.getEnderecos());
		return clienteRepository.save(entity);
	}


	public Cliente update(Cliente cliente) {
		Cliente newOjb = findById(cliente.getId());
		updateDate(newOjb, cliente);
		return clienteRepository.save(newOjb);
	}

	public void deleteById(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois há pedidos relacionadas");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente saveFromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);		
		
	}
	public Cliente saveFromDTO(ClienteNewDTO objDTO) {
		 Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		 Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		 Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid); 
		 
		 cli.getEnderecos().add(end);
		 cli.getTelefones().add(objDTO.getTelefone1());
		 if (objDTO.getTelefone2() != null) {
			 cli.getTelefones().add(objDTO.getTelefone2());
		 } if (objDTO.getTelefone3() != null) {
			 cli.getTelefones().add(objDTO.getTelefone3());	
		 }
		 
		 return cli;	
	}

	private void updateDate(Cliente newObj, Cliente cliente) {
		newObj.setNome(cliente.getNome());
		newObj.setEmail(cliente.getEmail());
	}

}
