package br.com.desafio;

import br.com.desafio.dao.csv.CsvOperations;
import br.com.desafio.model.vo.ClienteVO;

import java.util.List;

public class Teste1 {

    public static void main(String[] args) {
        ClienteVO clienteVO = new ClienteVO("teste1", 1, "m", "01/01/01");

        CsvOperations.writeAClienteCsv(clienteVO);

        List<ClienteVO> clienteVOS = CsvOperations.readCsv();
        clienteVOS.forEach(System.out::println);
    }
}
