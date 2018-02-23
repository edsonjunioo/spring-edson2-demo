package com.example.demo.controller;


import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Funcionario;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClienteController extends Object {


    public ClienteController() {
    }

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/get")
    public List<Cliente> getAllCliente() {

        List<Cliente> cliente = clienteRepository.findAll();


        return cliente;
    }


    @PostMapping("/create")
    public String inserirCliente(@Valid @RequestBody Cliente cliente) {

        Cliente response = new Cliente(cliente.getCpf(),cliente.getNome(),cliente.getEndereco(),cliente.getTelefone());
        response.equals(clienteRepository.save(cliente));

        String sucess = " Inserção na tabela Funcionario efetuada com sucesso!\n"+
                " Verificar dados através da URL api/get_client";

        return sucess;
    }

    @GetMapping("/get/{codigo}")
    public ResponseEntity<Object> getClientebyId(@PathVariable(value = "codigo") Long clienteId) {
        verifyIfClientExists(clienteId);
        Cliente cliente = clienteRepository.findOne(clienteId);

        return ResponseEntity.ok().build();
    }

    // Update de cliente
    @PutMapping("/alter/{codigo}")
    public String updateClient(@PathVariable(value = "codigo") Long clienteId,
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

    // Delete a Veiculo
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "codigo") Long clienteId) {

        verifyIfClientExists(clienteId);
        Cliente cliente = clienteRepository.findOne(clienteId);
        clienteRepository.delete(cliente);
        return ResponseEntity.ok().build();
    }

    public void verifyIfClientExists(Long clienteId){
        if(clienteRepository.findOne(clienteId) == null)
            throw new ResourceNotFoundException("Student not Found for ID: " + clienteId);
    }


}
