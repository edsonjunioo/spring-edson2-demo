package com.example.demo.controller;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Avaria;
import com.example.demo.repository.AvariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/avaria")
public class AvariaContloller extends Object{

    public AvariaContloller() {
    }

    @Autowired
    AvariaRepository avariaRepository;

    @GetMapping("/get")
    public List<Avaria> listAllavaria(){

        List<Avaria> avaria = avariaRepository.findAll();

        return avaria;
    }


    @PostMapping("/create")
    public Avaria createAvaria(@Valid @RequestBody Avaria avaria) {

        Avaria response = new Avaria(avaria.getDescricao(), avaria.getValor());
        response.equals(avariaRepository.save(avaria));


        return response;
    }


    @GetMapping("/get/{codigo}")
    public ResponseEntity<Object> getNoteById(@PathVariable(value = "codigo") Long avariaId) {
        verifyIfAvariaExists(avariaId);
        Avaria veiculo = avariaRepository.findOne(avariaId);
        return ResponseEntity.ok().body(veiculo);
    }


    @PutMapping("/alter/{codigo}")
    public ResponseEntity<Avaria> updateNote(@PathVariable(value = "codigo") Long avariaId,
                                              @Valid @RequestBody Avaria avariaDetails) {

        verifyIfAvariaExists(avariaId);
        Avaria avaria = avariaRepository.findOne(avariaId);

        avaria.setValor(avariaDetails.getValor());
        avaria.setDescricao(avariaDetails.getDescricao());



        Avaria updatedAvaria = avariaRepository.save(avaria);
        return ResponseEntity.ok(updatedAvaria);

    }


    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "codigo") Long avariaId) {

        verifyIfAvariaExists(avariaId);
        Avaria avaria = avariaRepository.findOne(avariaId);
        avariaRepository.delete(avaria);
        return ResponseEntity.ok().build();
    }


    public void verifyIfAvariaExists(Long avariaId){
        if(avariaRepository.findOne(avariaId) == null)
            throw new ResourceNotFoundException("Funcionario not Found for ID: " + avariaId);
    }




}