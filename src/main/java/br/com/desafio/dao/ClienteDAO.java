package br.com.desafio.dao;

import br.com.desafio.model.Cliente;
import br.com.desafio.model.MetaData;
import br.com.desafio.util.JsonParse;
import org.json.JSONObject;

import java.util.List;

public class ClienteDAO {

    private JsonParse jsonParse;

    public List<Cliente> findAll() {
        JSONObject a = ClienteApi.requisicaoApi();
        jsonParse = new JsonParse();
        return jsonParse.fromJson(a);
    }


}
