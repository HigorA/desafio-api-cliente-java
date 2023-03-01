package br.com.desafio.controllers;

import br.com.desafio.model.Cliente;
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

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Cliente>> findAll(
            @PathVariable("pageNumber") Integer pageNumber,
            @PathVariable("pageSize") Integer pageSize,
            @RequestParam(required = false) String idade) {
        ClienteParametroFiltro filtro = new ClienteParametroFiltro();
        filtro.setIdade(idade);
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(filtro));
    }
}
