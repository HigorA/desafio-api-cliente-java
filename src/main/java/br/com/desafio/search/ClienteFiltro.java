package br.com.desafio.search;

import br.com.desafio.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteFiltro {

    public static List<Cliente> filtro(ClienteParametroFiltro param, List<Cliente> clientes) {
        List<Cliente> filtrado = new ArrayList<>();
        if (param.getMenorIdade() != null && param.getMaiorIdade() != null) {
            filtrado = clientes
                    .stream()
                    .filter(cliente -> cliente.getIdade() >= Integer.parseInt(param.getMenorIdade()) &&
                    cliente.getIdade() <= Integer.parseInt(param.getMaiorIdade()))
                    .collect(Collectors.toList());
        }
        if (param.getSexo() != null) {
            filtrado = clientes
                    .stream()
                    .filter(cliente -> cliente.getSexo().equalsIgnoreCase(param.getSexo()))
                    .collect(Collectors.toList());
        }
        if (param.getMesAniversario() != null) {
            filtrado = clientes
                    .stream()
                    .filter(cliente -> Integer.parseInt(cliente.getAniversario().substring(3,5)) == Integer.parseInt(param.getMesAniversario()))
                    .collect(Collectors.toList());
        }
        if (param.getMenorIdade() == null && param.getMaiorIdade() == null && param.getSexo() == null && param.getMesAniversario() == null) {
            return clientes;
        }

        return filtrado;
    }
}
