package com.matera.web.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ContasDoMes {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Informe a descrição.")
	@Size(max = 150, message = "A descrição deve ter no máximo 150 caracteres.")
	private String descricao;

	@NotNull(message = "Informe o valor.")
	@Positive(message = "O valor deve ser maior que zero.")
	private Double valor;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Informe a data de cadastro.")
	@PastOrPresent(message = "A data não pode estar no futuro.")
	private LocalDate dtCadastro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getDtCadastroFormatada() {
		return dtCadastro == null ? "" : dtCadastro.format(DATE_FORMATTER);
	}
}
