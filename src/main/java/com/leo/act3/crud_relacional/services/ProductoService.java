package com.leo.act3.crud_relacional.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.act3.crud_relacional.models.Categoria;
import com.leo.act3.crud_relacional.models.Producto;
import com.leo.act3.crud_relacional.repositories.CategoriaRepository;
import com.leo.act3.crud_relacional.repositories.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // 1. LEER TODOS
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // 2. LEER POR ID
    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    // 3. CREAR O ACTUALIZAR
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    // 4. ELIMINAR
    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }
    
    // Método extra para inicializar categorías de prueba si la BD está vacía
    public void inicializarCategoriasSiVacio() {
        if (categoriaRepository.count() == 0) {
            categoriaRepository.save(new Categoria(null, "Electrónica"));
            categoriaRepository.save(new Categoria(null, "Hogar y Cocina"));
            categoriaRepository.save(new Categoria(null, "Ropa y Moda"));
        }
    }
}