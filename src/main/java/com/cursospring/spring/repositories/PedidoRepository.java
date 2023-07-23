package com.cursospring.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursospring.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
}
