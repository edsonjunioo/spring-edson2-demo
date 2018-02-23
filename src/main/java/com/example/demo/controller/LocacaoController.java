package com.example.demo.controller;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Locacao;
import com.example.demo.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public class LocacaoController extends Object {

    public LocacaoController() {
    }

    @Autowired
    LocacaoRepository locacaoRepository;


    //buscar locações
    @GetMapping("/get")
    public List<Locacao> getLocations() {

        List<Locacao> locacao = locacaoRepository.findAll();

        return locacao;
    }

    //criar locações
    @PostMapping("/create")
    public Locacao doLocations(@Valid @RequestBody Locacao locacao){

        Locacao response = new Locacao(locacao.getData(),locacao.getHora());
        response.equals(locacaoRepository.save(locacao));

        return response;

    }


    @GetMapping("/get/{codigo}")
    public ResponseEntity<Object> getLocationbyId(@PathVariable(value = "codigo") Long locacaoId){
        verifyIfCarExists(locacaoId);
        Locacao locacao = locacaoRepository.findOne(locacaoId);

        return ResponseEntity.ok().build();

    }



    public void verifyIfCarExists(Long locacaoId){
        if(locacaoRepository.findOne(locacaoId) == null)
            throw new ResourceNotFoundException("Locacao not found para o ID: " + locacaoId);

    }

}
