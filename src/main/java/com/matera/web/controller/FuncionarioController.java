package com.matera.web.controller;

import com.matera.web.model.Funcionario;
import com.matera.web.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FuncionarioController {

	private final FuncionarioService service;

	public FuncionarioController(FuncionarioService service) {
		this.service = service;
	}

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("redirect:/funcionario");
	}

	@GetMapping("/funcionario")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("funcionario");
		mv.addObject("funcionarios", service.findAll());
		return mv;
	}

	@GetMapping("/funcionario/add")
	public ModelAndView add(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("funcionarioAdd");
		mv.addObject("funcionario", funcionario);
		mv.addObject("editando", false);
		return mv;
	}

	@GetMapping("/funcionario/edit/{cpf}")
	public ModelAndView edit(@PathVariable("cpf") String cpf) {
		Funcionario funcionario = service.findOne(cpf)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
		ModelAndView mv = add(funcionario);
		mv.addObject("editando", true); //o CPF é a chave primária e não pode ser alterado na edição
		return mv;
	}

	@PostMapping("/funcionario/delete/{cpf}")
	public ModelAndView delete(@PathVariable("cpf") String cpf, RedirectAttributes attributes) {
		service.delete(cpf);
		attributes.addFlashAttribute("mensagem", "Funcionário excluído com sucesso.");
		return new ModelAndView("redirect:/funcionario");
	}

	@PostMapping("/funcionario/save")
	public ModelAndView save(@Valid Funcionario funcionario, BindingResult result,
			@RequestParam(name = "editando", defaultValue = "false") boolean editando,
			RedirectAttributes attributes) {
		if(result.hasErrors()) {
			ModelAndView mv = add(funcionario);
			mv.addObject("editando", editando);
			return mv;
		}
		service.save(funcionario);
		attributes.addFlashAttribute("mensagem", editando
				? "Funcionário atualizado com sucesso."
				: "Funcionário cadastrado com sucesso.");
		return new ModelAndView("redirect:/funcionario");
	}
}
