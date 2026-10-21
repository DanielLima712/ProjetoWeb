package com.matera.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matera.web.model.Funcionario;

@Repository //Define a classe como um bean do Spring
public interface FuncionarioRepository extends JpaRepository<Funcionario, String> { }
//Deve estender JpaRepository e declarar a entidade (Funcionario) e o tipo de chave primária (String)
