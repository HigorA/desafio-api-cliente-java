package br.com.desafio;

import br.com.desafio.dao.ClienteDAO;
import br.com.desafio.dao.csv.CsvOperations;
import br.com.desafio.model.Cliente;
import br.com.desafio.model.vo.ClienteVO;
import br.com.desafio.util.ClienteUtil;

import java.util.List;

public class Teste1 {

    public static void main(String[] args) {
//        ClienteVO clienteVO = new ClienteVO("teste1", 1, "m", "01/01/01");
//
//        CsvOperations.writeAClienteCsv(clienteVO);
//
//        List<ClienteVO> clienteVOS = CsvOperations.readCsv();
//        clienteVOS.forEach(System.out::println);

//        ClienteDAO dao = new ClienteDAO();
//        List<Cliente> c = dao.findAll();
//        c.forEach(System.out::println);
//        c.forEach(ce -> ce.setAniversario(ClienteUtil.convertAniversario(ce.getAniversario(), ce.getIdade())));
//        c.forEach(System.out::println);
        System.out.println(ClienteUtil.convertAniversario("01/1", 23));
    }
}
