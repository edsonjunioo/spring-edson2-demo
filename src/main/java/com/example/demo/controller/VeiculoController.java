package com.example.demo.controller;


import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController extends Object{


    public VeiculoController() {
    }


    @Autowired
    VeiculoRepository veiculoRepository;


    @GetMapping("/get")
    public List<Veiculo> getAllVeiculos() {

        List<Veiculo> veiculo = veiculoRepository.findAll();

        return veiculo;
    }

    @PostMapping("/create")
    public String createVeiculo(@Valid @RequestBody Veiculo veiculo) {

        Veiculo response = new Veiculo(veiculo.getMarca(), veiculo.getModelo(), veiculo.getData(), veiculo.getAno(), veiculo.getValor(), veiculo.getObservacao());
        response.equals(veiculoRepository.save(veiculo));

        String sucess = " Inserção na tabela Funcionario efetuada com sucesso!\n"+
                " Verificar dados através da URL api/get_employer";


        return sucess;
    }


    @GetMapping("/get/{codigo}")
    public ResponseEntity<Object> getNoteById(@PathVariable(value = "codigo") Long veiculoId) {
        verifyIfCarExists(veiculoId);
        Veiculo veiculo = veiculoRepository.findOne(veiculoId);
        return ResponseEntity.ok().body(veiculo);
    }


    @PutMapping("/alter/{codigo}")
    public ResponseEntity<Veiculo> updateNote(@PathVariable(value = "codigo") Long veiculoId,
                                              @Valid @RequestBody Veiculo veiculoDetails) {

        verifyIfCarExists(veiculoId);
        Veiculo veiculo = veiculoRepository.findOne(veiculoId);

        veiculo.setMarca(veiculoDetails.getMarca());
        veiculo.setModelo(veiculoDetails.getModelo());
        veiculo.setData(veiculoDetails.getData());
        veiculo.setAno(veiculoDetails.getAno());
        veiculo.setValor(veiculoDetails.getValor());
        veiculo.setObservacao(veiculoDetails.getObservacao());


        Veiculo updatedVeiculo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(updatedVeiculo);

    }


    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "codigo") Long veiculoId) {

        verifyIfCarExists(veiculoId);
        Veiculo veiculo = veiculoRepository.findOne(veiculoId);
        veiculoRepository.delete(veiculo);
        return ResponseEntity.ok().build();
    }



    public void verifyIfCarExists(Long veiculoId){
        if(veiculoRepository.findOne(veiculoId) == null)
            throw new ResourceNotFoundException("Veiculo not Found for ID: " + veiculoId);
    }


}
