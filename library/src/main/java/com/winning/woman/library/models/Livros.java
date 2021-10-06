package com.winning.woman.library.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "Livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livros implements Serializable {
	
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;
	private String autor;
	private String dataAdicionado;
	private String dataConclusao;
	private int nota;
	private String status;
}
