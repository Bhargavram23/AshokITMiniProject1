package com.RamaIT.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrroController {
	@ExceptionHandler(Exception.class)
	public String loadErrorPage(Model model,Exception e) {
		model.addAttribute("exceptionMessage",e.getMessage());
		return "error";
	}

}
