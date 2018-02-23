package com.example.demo.controller;


import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController extends Object{

    public CategoriaController() {
    }

    @Autowired
    CategoriaRepository categoriaRepository;


    @GetMapping("/get")
    public List<Categoria> getAlldescriptions() {

        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias;

    }

    @PostMapping("/create")
    public String criarDescription(@Valid @RequestBody Categoria categoria) {

        Categoria response = new Categoria(categoria.getDescricao());
        response.equals(categoriaRepository.save(categoria));

        String sucess = " Inserção na tabela Funcionario efetuada com sucesso!\n"+
                " Verificar dados através da URL api/get_employer";


        return sucess;

    }



}
