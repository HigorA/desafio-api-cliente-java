package br.com.desafio.services;

import br.com.desafio.dao.ClienteApi;
import br.com.desafio.dao.ClienteDAO;
import br.com.desafio.model.Cliente;
import br.com.desafio.model.response.ClienteResponse;
import br.com.desafio.search.ClienteFiltro;
import br.com.desafio.search.ClienteParametroFiltro;
import br.com.desafio.util.ClienteUtil;
import br.com.desafio.util.JsonParse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {


    private ClienteDAO dao = new ClienteDAO();
    private ClienteUtil util = new ClienteUtil();

    public List<Cliente> findAll(ClienteParametroFiltro filtro) {
        List<Cliente> clientes = dao.findAll();
        clientes = clientes.stream().sorted(Cliente::compareTo).collect(Collectors.toList());
        clientes.forEach(c -> c.setAniversario(util.convertAniversario(c.getAniversario(), c.getIdade())));
        return ClienteFiltro.filtro(filtro, clientes);
    }

    public List<ClienteResponse> findAllWithId(ClienteParametroFiltro filtro) {
        List<Cliente> clientes = dao.findAll();
        List<ClienteResponse> responses = new ArrayList<>();
        clientes.forEach(cliente -> responses.add(new ClienteResponse(cliente.getNome(),
                cliente.getIdade(), cliente.getSexo(), cliente.getAniversario())));
        return responses;
    }
}
