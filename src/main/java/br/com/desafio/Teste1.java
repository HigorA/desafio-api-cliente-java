package br.com.desafio;

import br.com.desafio.dao.csv.OpenCsvRead;
import br.com.desafio.model.Cliente;
import br.com.desafio.model.response.ClienteResponse;
import br.com.desafio.search.ClienteParametroFiltro;
import br.com.desafio.services.ClienteService;
import br.com.desafio.util.ClienteUtil;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Teste1 {

    public static void main(String[] args) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {

        ClienteService service = new ClienteService();
        ClienteParametroFiltro filtro = new ClienteParametroFiltro();
        ClienteUtil util = new ClienteUtil();
        filtro.setMesAniversario("2");
        List<Cliente> clientes = service.findAll(filtro);
        List<ClienteResponse> responses = new ArrayList<>();
        List<ClienteResponse> finalResponses = responses;
        clientes.forEach(c -> finalResponses.add(new ClienteResponse(c.getNome(), c.getIdade(), c.getSexo(), c.getAniversario())));
        responses = util.incrementId(finalResponses);
        responses.forEach(System.out::println);
//        responses.forEach(System.out::println);
        OpenCsvRead.writeListToCsv(responses);
//        OpenCsvRead.writeAClienteCsv(responses.get(0));
//        OpenCsvRead.readCsv();
//        List asd = responses.stream().filter(clienteResponse -> !(clienteResponse.getId() == 1)).collect(Collectors.toList());
//        asd.forEach(System.out::println);
    }
}
