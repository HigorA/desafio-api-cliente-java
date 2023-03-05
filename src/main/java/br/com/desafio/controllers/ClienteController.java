package br.com.desafio.controllers;

import br.com.desafio.controllers.OpenApi.ControllerOpenApi;
import br.com.desafio.model.PagedResource;
import br.com.desafio.model.vo.ClienteVO;
import br.com.desafio.search.ClienteParametroFiltro;
import br.com.desafio.services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Endpoint para gerenciar clientes.")
public class ClienteController implements ControllerOpenApi {

    @Autowired
    private ClienteService service;

    @GetMapping("/api")
    public ResponseEntity<PagedResource> findAllFromApi(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer menorIdade,
            @RequestParam(required = false) Integer maiorIdade,
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String aniversario
    )
    {
        ClienteParametroFiltro filtrate = new ClienteParametroFiltro(menorIdade, maiorIdade, sexo, aniversario);
        PagedResource metaData = service.findAll(filtrate, pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(metaData);
    }

    @GetMapping("/api-with-id")
    public ResponseEntity<PagedResource> findAllFromApiWithId(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer menorIdade,
            @RequestParam(required = false) Integer maiorIdade,
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String aniversario
    ) {
        ClienteParametroFiltro filtrate = new ClienteParametroFiltro(menorIdade, maiorIdade, sexo, aniversario);
        PagedResource metaData = service.findAllWithId(filtrate, pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(metaData);
    }

    @GetMapping("/csv")
    public ResponseEntity<PagedResource> findAllFromCsv(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer menorIdade,
            @RequestParam(required = false) Integer maiorIdade,
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String aniversario
    ) {
        ClienteParametroFiltro filtrate = new ClienteParametroFiltro(menorIdade, maiorIdade, sexo, aniversario);
        PagedResource metaData = service.findAllFromCsv(filtrate, pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(metaData);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClienteVO clienteVO) {
        service.save(clienteVO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ClienteVO clienteVO) {
        service.update(clienteVO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathParam("id") Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
