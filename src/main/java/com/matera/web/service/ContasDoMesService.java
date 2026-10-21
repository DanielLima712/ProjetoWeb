package com.matera.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.matera.web.model.ContasDoMes;
import com.matera.web.repository.ContasDoMesRepository;

@Service
public class ContasDoMesService {

	private final ContasDoMesRepository repository;

	public ContasDoMesService(ContasDoMesRepository repository) {
		this.repository = repository;
	}

	//Retorna uma lista com todas as contas
	public List<ContasDoMes> findAll() {
		return repository.findAll();
	}

	//Retorna uma conta a partir do ID
	public Optional<ContasDoMes> findOne(Integer id) {
		return repository.findById(id);
	}

	//Salva ou atualiza uma conta
	public ContasDoMes save(ContasDoMes conta) {
		return repository.save(conta);
	}

	//Exclui uma conta
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
