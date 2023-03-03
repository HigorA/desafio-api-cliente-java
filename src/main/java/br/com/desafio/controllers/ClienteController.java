package br.com.desafio.controllers;

import br.com.desafio.model.Cliente;
import br.com.desafio.model.MetaData;
import br.com.desafio.model.response.ClienteResponse;
import br.com.desafio.search.ClienteParametroFiltro;
import br.com.desafio.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<MetaData> findAll(
            @RequestParam(required = false) String menorIdade,
            @RequestParam(required = false) String maiorIdade,
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String aniversario
    )
    {
        ClienteParametroFiltro filtro = new ClienteParametroFiltro();
        filtro.setMenorIdade(menorIdade);
        filtro.setMaiorIdade(maiorIdade);
        filtro.setSexo(sexo);
        filtro.setMesAniversario(aniversario);
        List<Cliente> clientes = service.findAll(filtro);
        MetaData metaData = new MetaData(clientes.size(), clientes);
        return ResponseEntity.status(HttpStatus.OK).body(metaData);
    }

//    @GetMapping
//    public ResponseEntity<MetaData> findAllWithId() {
//        return ResponseEntity.status(HttpStatus.OK).body();
//    }
//
//    @GetMapping
//    public ResponseEntity<MetaData> findAllFromCsv() {
//        return ResponseEntity.status(HttpStatus.OK).body();
//    }
}
