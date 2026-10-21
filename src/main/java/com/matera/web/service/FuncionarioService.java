package com.matera.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.matera.web.model.Funcionario;
import com.matera.web.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	private final FuncionarioRepository repository;

	public FuncionarioService(FuncionarioRepository repository) {
		this.repository = repository;
	}

	//Retorna uma lista com todos os funcionários
	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	//Retorna um funcionário a partir do CPF
	public Optional<Funcionario> findOne(String cpf) {
		return repository.findById(cpf);
	}

	//Salva ou atualiza um funcionário
	public Funcionario save(Funcionario funcionario) {
		return repository.save(funcionario);
	}

	//Exclui um funcionário
	public void delete(String cpf) {
		repository.deleteById(cpf);
	}

}
