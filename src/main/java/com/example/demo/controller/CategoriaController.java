package com.example.demo.controller;


import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
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

    // Get a Single Note
    @GetMapping("/get/{codigo}")
    public ResponseEntity<Object> getNoteById(@PathVariable(value = "codigo") Long categoriaId) {
        verifyIfCategoriaExists(categoriaId);
        Categoria categoria = categoriaRepository.findOne(categoriaId);
        return ResponseEntity.ok().body(categoria);
    }

    // Update a Veiculo
    @PutMapping("/alter/{codigo}")
    public ResponseEntity<Categoria> updateNote(@PathVariable(value = "codigo") Long categoriaId,
                                              @Valid @RequestBody Categoria categoriaDetails) {

        verifyIfCategoriaExists(categoriaId);
        Categoria categoria = categoriaRepository.findOne(categoriaId);

        categoria.setDescricao(categoriaDetails.getDescricao());



        Categoria updatedCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(updatedCategoria);

    }


    // Delete a Veiculo
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteMulta(@PathVariable(value = "codigo") Long categoriaId) {

        verifyIfCategoriaExists(categoriaId);
        Categoria categoria = categoriaRepository.findOne(categoriaId);
        categoriaRepository.delete(categoria);
        return ResponseEntity.ok().build();
    }


    public void verifyIfCategoriaExists(Long categoriaId){
        if(categoriaRepository.findOne(categoriaId) == null)
            throw new ResourceNotFoundException("Veiculo not Found for ID: " + categoriaId);
    }



}
