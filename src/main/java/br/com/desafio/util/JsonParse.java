package br.com.desafio.util;

import br.com.desafio.model.Cliente;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonParse {

    public static List<Cliente> fromJson(String json) {
        int pri = json.indexOf('[');
        int ult = json.lastIndexOf(']');
        String teste1 = json.substring(pri + 1, ult);
        String teste2 = teste1.replace("},{", "}/{");
        System.out.println(teste2.getClass());
        List<String> teste3 = List.of(teste2.split("/"));


        Gson gson = new Gson();
        List<Cliente> clientes = new ArrayList<>();
        teste3.forEach(t -> clientes.add(gson.fromJson(t , Cliente.class)));
        return clientes;
    }
}
