package com.example.demo.controller;


import com.example.demo.VeiculoService;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class VeiculoController extends Object{

    //private String importFile = "/query.sql";


    public VeiculoController() {
    }





    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CategoriaRepository categoriaRepository;


    @Autowired
    FuncionarioRepository funcionarioRepository;


    @Autowired
    MultaRepository multaRepository;

    @Autowired
    LocacaoRepository locacaoRepository;


    /*
    @RequestMapping("/veiculos")
    public Veiculo getListaVeiculos()
    {
        Veiculo veiculo1 = new Veiculo("1","2","3","4","5","6");
        return veiculo1;
    }
    */


    @RequestMapping("/database")
    public String index() {
        return "index";
    }


    @GetMapping("/get_veiculos")
    public List<Veiculo> getAllVeiculos() {

        List<Veiculo> veiculo = veiculoRepository.findAll();

        return veiculo;
    }

    // Create a new Note
    @PostMapping("/create_veiculos")
    public String createVeiculo(@Valid @RequestBody Veiculo veiculo) {

        Veiculo response = new Veiculo(veiculo.getMarca(), veiculo.getModelo(), veiculo.getData(), veiculo.getAno(), veiculo.getValor(), veiculo.getObservacao());
        response.equals(veiculoRepository.save(veiculo));

        String sucess = " Inserção na tabela Funcionario efetuada com sucesso!\n"+
                " Verificar dados através da URL api/get_employer";


        return sucess;
    }

    // Get a Single Note
    @GetMapping("/veiculo/{codigo}")
    public ResponseEntity<Object> getNoteById(@PathVariable(value = "codigo") Long veiculoId) {
        verifyIfCarExists(veiculoId);
        Veiculo veiculo = veiculoRepository.findOne(veiculoId);
        return ResponseEntity.ok().body(veiculo);
    }


    // Update a Veiculo
    @PutMapping("/alter_veiculo/{codigo}")
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


    // Delete a Veiculo
    @DeleteMapping("/delete_veiculo/{codigo}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "codigo") Long veiculoId) {

        verifyIfCarExists(veiculoId);
        Veiculo veiculo = veiculoRepository.findOne(veiculoId);
        veiculoRepository.delete(veiculo);
        return ResponseEntity.ok().build();
    }


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
    public String updateNote(@PathVariable(value = "codigo") Long noteId,
                                              @Valid @RequestBody Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findOne(noteId);
        if (cliente == null) {
            String erro = "codigo não Encontrado";
            return erro;
        }


        cliente.setNome(clienteDetails.getNome());
        cliente.setCpf(clienteDetails.getCpf());
        cliente.setEndereco(clienteDetails.getEndereco());
        cliente.setTelefone(clienteDetails.getTelefone());


        Cliente updatedCliente = clienteRepository.save(cliente);

        String sucess = "Mensagem alterada com sucesso";

        return sucess;

    }


    /*
    //start script for run sql service
    @GetMapping("/runsql")
    public List<Cliente> getSqlCliente(){
        EntityManager manager = getEntityManager();
        Query q = manager.createNativeQuery(query.sql);
        q.executeUpdate();

    }
    */

    @GetMapping("/get_descricao")
    public List<Categoria> getAlldescriptions() {

        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias;

    }

    @PostMapping("/create_descricao")
    public String criarDescription(@Valid @RequestBody Categoria categoria) {

        Categoria response = new Categoria(categoria.getDescricao());
        response.equals(categoriaRepository.save(categoria));

        String sucess = " Inserção na tabela Funcionario efetuada com sucesso!\n"+
                " Verificar dados através da URL api/get_employer";


        return sucess;

    }


    @GetMapping("/get_employer")
    public List<Funcionario> getAllemployers() {

        List<Funcionario> funcionario = funcionarioRepository.findAll();

        return funcionario;
    }


    @PostMapping("/create_employer")
    public String criarEmployer(@Valid @RequestBody Funcionario funcionario) {

        Funcionario response = new Funcionario(funcionario.getNome(),funcionario.getCpf(),funcionario.getEndereco(),funcionario.getTelefone());
        response.equals(funcionarioRepository.save(response));

        String sucess = " Inserção na tabela Funcionario efetuada com sucesso!\n"+
                        " Verificar dados através da URL api/get_employer";


        return sucess;

    }


    //buscar multas
    @GetMapping("/get_multa")
    public List<Multa> buscarMulta() {


        List<Multa> response = new ArrayList<>();
        response.addAll(multaRepository.findAll());


        return response;

    }


    //criar multas
    @PostMapping("/create_multa")
    public String criarMulta(@Valid @RequestBody Multa multa) {

        Multa response = new Multa(multa.getStatus());
        response.equals(multaRepository.save(multa));



        String sucess = " Inserção enviado com sucesso!\n" +
                " Validar consulta atráves da URL api/get_multa ";

        return sucess;


    }

    //buscar locações
    @GetMapping("/get_locations")
    public List<Locacao> getLocations() {

        List<Locacao> locacao = locacaoRepository.findAll();

        return locacao;
    }

    //criar locações
    @PostMapping("/do_location")
    public Locacao doLocations(@Valid @RequestBody Locacao locacao){

        Locacao response = new Locacao(locacao.getData(),locacao.getHora());
        response.equals(locacaoRepository.save(locacao));

        return response;

    }

    public void verifyIfCarExists(Long veiculoId){
        if(veiculoRepository.findOne(veiculoId) == null)
            throw new ResourceNotFoundException("Student not Found for ID: " + veiculoId);
    }


}
