package br.com.desafio.search;

import br.com.desafio.model.Cliente;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteFiltro {

    public static List<Cliente> filtro(ClienteParametroFiltro param, List<Cliente> clientes) {
        List<Cliente> filtrado = new ArrayList<>();
        if (param.getIdade() != null) {
            filtrado = clientes.stream().filter(cliente -> cliente.getIdade() > Integer.parseInt(param.getIdade())).collect(Collectors.toList());
        }
        if (param.getSexo() != null) {
            filtrado = clientes.stream().filter(cliente -> cliente.getSexo().equals(param.getSexo())).collect(Collectors.toList());
        }

        if(param.getIdade() == null && param.getAniversario() == null && param.getSexo() == null) {
            return clientes;
        }
        return filtrado;
    }
}
