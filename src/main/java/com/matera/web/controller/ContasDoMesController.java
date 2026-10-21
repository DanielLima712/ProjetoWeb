package com.matera.web.controller;

import com.matera.web.model.ContasDoMes;
import com.matera.web.service.ContasDoMesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContasDoMesController {

	private final ContasDoMesService service;

	public ContasDoMesController(ContasDoMesService service) {
		this.service = service;
	}

	@GetMapping("/contas")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("contas");
		mv.addObject("contas", service.findAll());
		return mv;
	}

	@GetMapping("/contas/add")
	public ModelAndView add(ContasDoMes contasDoMes) {
		ModelAndView mv = new ModelAndView("contasAdd");
		mv.addObject("contasDoMes", contasDoMes);
		return mv;
	}

	@GetMapping("/contas/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id) {
		ContasDoMes conta = service.findOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
		return add(conta);
	}

	@PostMapping("/contas/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
		service.delete(id);
		attributes.addFlashAttribute("mensagem", "Conta excluída com sucesso.");
		return new ModelAndView("redirect:/contas");
	}

	@PostMapping("/contas/save")
	public ModelAndView save(@Valid ContasDoMes contasDoMes, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return add(contasDoMes);
		}
		boolean novaConta = contasDoMes.getId() == null;
		service.save(contasDoMes);
		attributes.addFlashAttribute("mensagem", novaConta
				? "Conta cadastrada com sucesso."
				: "Conta atualizada com sucesso.");
		return new ModelAndView("redirect:/contas");
	}
}
