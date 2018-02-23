package com.example.demo.controller;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Funcionario;
import com.example.demo.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("Employe")
public class FuncionarioController extends Object {

    public FuncionarioController() {
    }

    @Autowired
    FuncionarioRepository funcionarioRepository;


    @GetMapping("/get")
    public List<Funcionario> getAllemployers() {

        List<Funcionario> funcionario = funcionarioRepository.findAll();

        return funcionario;
    }


    @PostMapping("/create")
    public String criarEmployer(@Valid @RequestBody Funcionario funcionario) {

        Funcionario response = new Funcionario(funcionario.getNome(),funcionario.getCpf(),funcionario.getEndereco(),funcionario.getTelefone());
        response.equals(funcionarioRepository.save(response));

        String sucess = " Inserção na tabela Funcionario efetuada com sucesso!\n"+
                " Verificar dados através da URL api/get_employer";


        return sucess;

    }


    public void verifyIfClientExists(Long funcionarioId){
        if(funcionarioRepository.findOne(funcionarioId) == null)
            throw new ResourceNotFoundException("Funcionario not Found for ID: " + funcionarioId);
    }

}
