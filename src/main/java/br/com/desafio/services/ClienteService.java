package br.com.desafio.services;

import br.com.desafio.dao.ClienteApi;
import br.com.desafio.model.Cliente;
import br.com.desafio.search.ClienteFiltro;
import br.com.desafio.search.ClienteParametroFiltro;
import br.com.desafio.util.JsonParse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {


    private ClienteApi dao = new ClienteApi();

    public List<Cliente> findAll(ClienteParametroFiltro filtro) {
        List<Cliente> clientes = JsonParse.fromJson(dao.requisicaoApi());
        clientes = clientes.stream().sorted(Cliente::compareTo).collect(Collectors.toList());
        return ClienteFiltro.filtro(filtro, clientes);
    }
}
