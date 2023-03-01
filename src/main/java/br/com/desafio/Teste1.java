package br.com.desafio;

import br.com.desafio.model.Cliente;
import br.com.desafio.model.response.ClienteResponse;
import br.com.desafio.search.ClienteParametroFiltro;
import br.com.desafio.services.ClienteService;
import br.com.desafio.util.ClienteUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Teste1 {

    public static void main(String[] args) {

        ClienteService service = new ClienteService();
        ClienteParametroFiltro filtro = new ClienteParametroFiltro();
        filtro.setIdade("20");
        List<Cliente> clientes = service.findAll(filtro);
//        clientes.forEach(System.out::println);
//        String ani = clientes.get(0).getAniversario();
//        System.out.println(ani);
//
//        String[] data = ani.split("-");
//
//        LocalDate localDate = LocalDate.of(LocalDate.now().getYear() - clientes.get(0).getIdade(),
//                Integer.parseInt(data[1]), Integer.parseInt(data[0]));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
//        System.out.println(localDate.format(formatter));
        ClienteUtil util = new ClienteUtil();
        List<ClienteResponse> responses = new ArrayList<>();
        clientes.forEach(cliente -> responses.add(util.convertCliente(cliente)));
        responses.forEach(System.out::println);
    }
}
