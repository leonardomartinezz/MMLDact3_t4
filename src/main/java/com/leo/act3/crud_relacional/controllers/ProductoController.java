package com.leo.act3.crud_relacional.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leo.act3.crud_relacional.models.Producto;
import com.leo.act3.crud_relacional.services.ProductoService;

@Controller
@RequestMapping("/inventario")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // A) VISTA: LISTAR TODOS (LEER)
    @GetMapping
    public String listar(Model model) {
        productoService.inicializarCategoriasSiVacio(); // Crea categorías por defecto si no existen
        model.addAttribute("productos", productoService.listarTodos());
        return "lista";
    }

    // B) VISTA: FORMULARIO PARA CREAR
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", productoService.listarCategorias());
        return "formulario";
    }

    // C) VISTA: FORMULARIO PARA ACTUALIZAR (EDITAR)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) return "redirect:/inventario";
        
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", productoService.listarCategorias());
        return "formulario";
    }

    // D) ACCIÓN: GUARDAR (CREAR O ACTUALIZAR)
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/inventario";
    }

    // E) ACCIÓN: ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "redirect:/inventario";
    }

    // ==========================================
    // F) ENDPOINT REST PARA BRUNO / POSTMAN (JSON)
    // ==========================================
    @PostMapping(value = "/api/crear", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Producto crearDesdeBruno(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }
}