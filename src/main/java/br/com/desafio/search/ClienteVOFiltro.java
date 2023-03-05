package br.com.desafio.search;

import br.com.desafio.model.vo.ClienteVO;
import br.com.desafio.util.ClienteUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteVOFiltro {

    public static List<ClienteVO> filtro(ClienteParametroFiltro param, List<ClienteVO> clientes) {
        List<ClienteVO> filtrado = clientes;
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
                    .filter(cliente -> Integer.parseInt(cliente.getDataNascimento().substring(3,5)) == Integer.parseInt(param.getMesAniversario()))
                    .collect(Collectors.toList());
        }

        filtrado = ClienteUtil.incrementId(filtrado);
        return filtrado;
    }
}
