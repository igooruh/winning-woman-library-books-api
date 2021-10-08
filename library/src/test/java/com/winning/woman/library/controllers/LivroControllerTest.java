package com.winning.woman.library.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winning.woman.library.models.Livros;
import com.winning.woman.library.repositories.LivrosRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	LivrosRepository livrosRepository;
	
	Livros LIVRO_1 = new Livros(
			1l, 
			"Nome teste 1", 
			"Nome autor 1", 
			"2021-10-03", 
			"2021-10-05", 
			9, 
			"concluído"
		);
	
	Livros LIVRO_2 = new Livros(
			2l, 
			"Nome teste 2", 
			"Nome autor 2", 
			"2021-10-05", 
			"2021-10-06", 
			4, 
			"lendo"
		);
	
	Livros LIVRO_3 = new Livros(
			3l, 
			"Nome teste 3", 
			"Nome autor 3", 
			"2021-10-09", 
			"2021-10-10", 
			5, 
			"quero ler"
		);
	
	@Test
	public void getAllBooks_success() throws Exception {
		List<Livros> livros = new ArrayList<Livros>(Arrays.asList(LIVRO_1, LIVRO_2, LIVRO_3));
		
		Mockito.when(livrosRepository.findAll()).thenReturn(livros);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/livro")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("[2].nome", is("Nome teste 3")));
	}
	
	@Test
	public void saveBook_success() throws Exception {
		Livros newLivro = new Livros(
				4l,
				"Nome teste 4", 
				"Nome autor 4", 
				"2021-10-01", 
				"2021-10-04", 
				7, 
				"lendo"
				);
		
		Mockito.when(livrosRepository.save(newLivro)).thenReturn(newLivro);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/livro")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(newLivro));
		
		mockMvc.perform(mockRequest).andExpect(status().isOk());
	}
	
	@Test
	public void updateBook_success() throws Exception {
		Livros updateLivro = new Livros(
				1l,
				"Nome teste novo", 
				"Nome autor 1", 
				"2021-10-01", 
				"2021-10-04", 
				7, 
				"lendo"
				);
		
		Mockito.when(livrosRepository.findById(LIVRO_1.getId())).thenReturn(Optional.of(LIVRO_1));
		Mockito.when(livrosRepository.save(updateLivro)).thenReturn(updateLivro);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/livro/" + updateLivro.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(updateLivro));
		
		mockMvc.perform(mockRequest).andExpect(status().isOk())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.nome", is("Nome teste novo")));
	}
	
	@Test
	public void deletePatientById_success() throws Exception {
		Mockito.when(livrosRepository.findById(LIVRO_2.getId())).thenReturn(Optional.of(LIVRO_2));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/livro/2")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
}














