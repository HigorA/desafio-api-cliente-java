package br.com.desafio.util;

import br.com.desafio.model.Cliente;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParse {

    private Gson gson = new Gson();

    public List<Cliente> fromJson(JSONObject json) {
        JSONObject record = json.getJSONObject("record");
        JSONArray clientesJson = record.getJSONArray("clientes");
        List<Cliente> clientes = new ArrayList<>();
        clientesJson.forEach(j -> clientes.add(gson.fromJson(j.toString(), Cliente.class)));
        return clientes;
    }
}
