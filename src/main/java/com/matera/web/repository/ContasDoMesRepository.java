package com.matera.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matera.web.model.ContasDoMes;

@Repository
public interface ContasDoMesRepository extends JpaRepository<ContasDoMes, Integer> { }
