package com.example.demo.controller;


import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClienteController extends Object {


    public ClienteController() {
    }

    ClienteRepository clienteRepository;

    @GetMapping("/get_client")
    public List<Cliente> getAllCliente() {

        List<Cliente> cliente = clienteRepository.findAll();


        return cliente;
    }


    @PostMapping("/create_client")
    public String inserirCliente(@Valid @RequestBody Cliente cliente) {

        Cliente response = new Cliente(cliente.getCpf(),cliente.getNome(),cliente.getEndereco(),cliente.getTelefone());
        response.equals(clienteRepository.save(cliente));

        String sucess = " Inserção na tabela Funcionario efetuada com sucesso!\n"+
                " Verificar dados através da URL api/get_client";

        return sucess;
    }

    // Update de cliente
    @PutMapping("/alter_client/{codigo}")
    public String updateNote(@PathVariable(value = "codigo") Long clienteId,
                             @Valid @RequestBody Cliente clienteDetails) {


        verifyIfClientExists(clienteId);

        Cliente cliente = clienteRepository.findOne(clienteId);

        cliente.setNome(clienteDetails.getNome());
        cliente.setCpf(clienteDetails.getCpf());
        cliente.setEndereco(clienteDetails.getEndereco());
        cliente.setTelefone(clienteDetails.getTelefone());


        Cliente updatedCliente = clienteRepository.save(cliente);

        String sucess = "Mensagem alterada com sucesso";

        return sucess;

    }



    public void verifyIfClientExists(Long clienteId){
        if(clienteRepository.findOne(clienteId) == null)
            throw new ResourceNotFoundException("Student not Found for ID: " + clienteId);
    }


}
