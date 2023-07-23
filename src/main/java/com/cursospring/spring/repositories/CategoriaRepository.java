package com.cursospring.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursospring.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
}
