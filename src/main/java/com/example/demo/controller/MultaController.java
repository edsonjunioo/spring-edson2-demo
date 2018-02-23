package com.example.demo.controller;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Multa;
import com.example.demo.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/multa")
public class MultaController extends Object {

    public MultaController() {
    }

    @Autowired
    MultaRepository multaRepository;

    //buscar multas
    @GetMapping("/get")
    public List<Multa> buscarMulta() {


        List<Multa> response = new ArrayList<>();
        response.addAll(multaRepository.findAll());


        return response;

    }


    //criar multas
    @PostMapping("/create")
    public String criarMulta(@Valid @RequestBody Multa multa) {

        Multa response = new Multa(multa.getStatus());
        response.equals(multaRepository.save(multa));



        String sucess = " Inserção enviado com sucesso!\n" +
                " Validar consulta atráves da URL api/get_multa ";

        return sucess;


    }


    // Get a Single multa
    @GetMapping("/get/{codigo}")
    public ResponseEntity<Object> getNoteById(@PathVariable(value = "codigo") Long multaId) {
        verifyIfMultaExists(multaId);
        Multa multa = multaRepository.findOne(multaId);
        return ResponseEntity.ok().body(multa);
    }

    // Update a Veiculo
    @PutMapping("/alter/{codigo}")
    public ResponseEntity<Multa> updateNote(@PathVariable(value = "codigo") Long multaId,
                                              @Valid @RequestBody Multa multaDetails) {

        verifyIfMultaExists(multaId);
        Multa multa = multaRepository.findOne(multaId);

        multa.setStatus(multaDetails.getStatus());



        Multa updatedMulta = multaRepository.save(multa);
        return ResponseEntity.ok(updatedMulta);

    }


    // Delete a Veiculo
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteMulta(@PathVariable(value = "codigo") Long multaId) {

        verifyIfMultaExists(multaId);
        Multa multa = multaRepository.findOne(multaId);
        multaRepository.delete(multa);
        return ResponseEntity.ok().build();
    }

    public void verifyIfMultaExists(Long multaId){
        if(multaRepository.findOne(multaId) == null)
            throw new ResourceNotFoundException("Multa not Found for ID: " + multaId);
    }
}
