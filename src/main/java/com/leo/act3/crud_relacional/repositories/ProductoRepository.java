package com.leo.act3.crud_relacional.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leo.act3.crud_relacional.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}