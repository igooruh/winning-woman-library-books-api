package com.winning.woman.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winning.woman.library.models.Livros;

public interface LivrosRepository extends JpaRepository<Livros, Long> {

}
