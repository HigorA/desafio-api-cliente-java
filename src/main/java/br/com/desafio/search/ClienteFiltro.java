package br.com.desafio.search;

import br.com.desafio.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteFiltro {

    public static List<Cliente> filtro(ClienteParametroFiltro param, List<Cliente> clientes) {
        List<Cliente> filtrado = clientes;
        if (param.getMenorIdade() != null && param.getMaiorIdade() != null) {
            filtrado = filtrado
                    .stream()
                    .filter(cliente -> cliente.getIdade() >= param.getMenorIdade() &&
                    cliente.getIdade() <= param.getMaiorIdade())
                    .collect(Collectors.toList());
        }
        if (param.getSexo() != null) {
            filtrado = filtrado
                    .stream()
                    .filter(cliente -> cliente.getSexo().equalsIgnoreCase(param.getSexo()))
                    .collect(Collectors.toList());
        }
        if (param.getMesAniversario() != null) {
            filtrado = filtrado
                    .stream()
                    .filter(cliente -> Integer.parseInt(cliente.getAniversario().substring(3,5)) == Integer.parseInt(param.getMesAniversario()))
                    .collect(Collectors.toList());
        }
        return filtrado;
    }
}
