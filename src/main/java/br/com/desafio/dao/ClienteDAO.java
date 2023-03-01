package br.com.desafio.dao;

import br.com.desafio.model.Cliente;
import br.com.desafio.util.JsonParse;

import java.util.List;

public class ClienteDAO {

    public List<Cliente> findAll() {
        String a = ClienteApi.requisicaoApi();
        return JsonParse.fromJson(a);
    }


}
