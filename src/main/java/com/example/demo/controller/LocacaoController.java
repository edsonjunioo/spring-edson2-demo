package com.example.demo.controller;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Funcionario;
import com.example.demo.model.Locacao;
import com.example.demo.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocacaoController extends Object {

    public LocacaoController() {
    }

    @Autowired
    LocacaoRepository locacaoRepository;


    @GetMapping("/get")
    public List<Locacao> getLocations() {

        List<Locacao> locacao = locacaoRepository.findAll();

        return locacao;
    }

    @PostMapping("/create")
    public Locacao doLocations(@Valid @RequestBody Locacao locacao){

        Locacao response = new Locacao(locacao.getData(),locacao.getHora());
        response.equals(locacaoRepository.save(locacao));

        return response;

    }


    @GetMapping("/get/{codigo}")
    public ResponseEntity<Object> getLocationbyId(@PathVariable(value = "codigo") Long locacaoId){
        verifyIfLocacaoExists(locacaoId);
        Locacao locacao = locacaoRepository.findOne(locacaoId);

        return ResponseEntity.ok().build();

    }

    @PutMapping("/alter/{codigo}")
    public ResponseEntity<Locacao> updateNote(@PathVariable(value = "codigo") Long locacaoId,
                                                  @Valid @RequestBody Locacao locacaoDetails) {

        verifyIfLocacaoExists(locacaoId);
        Locacao locacao = locacaoRepository.findOne(locacaoId);

        locacao.setData(locacaoDetails.getData());
        locacao.setHora(locacaoDetails.getHora());


        Locacao updatedLocacao = locacaoRepository.save(locacao);
        return ResponseEntity.ok(updatedLocacao);

    }

    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "codigo") Long locacaoId) {

        verifyIfLocacaoExists(locacaoId);
        Locacao locacao = locacaoRepository.findOne(locacaoId);
        locacaoRepository.delete(locacao);
        return ResponseEntity.ok().build();
    }



    public void verifyIfLocacaoExists(Long locacaoId){
        if(locacaoRepository.findOne(locacaoId) == null)
            throw new ResourceNotFoundException("Locacao not found para o ID: " + locacaoId);

    }

}
