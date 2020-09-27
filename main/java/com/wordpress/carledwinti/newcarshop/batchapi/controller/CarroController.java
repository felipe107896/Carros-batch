package com.wordpress.carledwinti.newcarshop.batchapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordpress.carledwinti.newcarshop.batchapi.model.Carro;
import com.wordpress.carledwinti.newcarshop.batchapi.service.CarroService;

@RestController
@RequestMapping("/api")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping("/home")
	public String mensagem() {
		return "Bem vindo a api do batch";
	}
	
	
	@GetMapping("/home")
	public String execute() {
		return carroService.batchExecute().toString();
	}
	
	@GetMapping("/carros")
	public List<Carro> todos() {
		return carroService.findAll();
	}
	

}
