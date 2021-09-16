package com.winning.woman.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winning.woman.library.models.Livros;
import com.winning.woman.library.repositories.LivrosRepository;

@RestController()
@RequestMapping(value = "/api")
public class LivroController {
	
	@Autowired
	LivrosRepository livrosRepository;

	@PostMapping("/livro")
	public void salvarLivros(@RequestBody Livros livro) {
		livrosRepository.save(livro);
	}
	
	@GetMapping("/livro") 
	public List<Livros> ListaTodosLivros() {
		return livrosRepository.findAll();
	}
	
	@PutMapping("/livro/{id}")
	public Livros atualizaLivro(@RequestBody Livros livros) {
		return livrosRepository.save(livros);
	}
	
	@DeleteMapping("/livro")
	public void deletaLivro(@RequestBody Livros livros) {
		livrosRepository.delete(livros);
	}
}
