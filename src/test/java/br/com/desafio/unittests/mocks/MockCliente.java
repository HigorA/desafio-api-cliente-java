package br.com.desafio.unittests.mocks;

import br.com.desafio.model.Cliente;
import br.com.desafio.model.vo.ClienteVO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MockCliente {

    public Cliente mockEntity() {
        return mockEntity(0);
    }

    public ClienteVO mockVO() {
        return mockVO(0);
    }

    public Cliente mockEntity(Integer number) {
        Cliente cliente = new Cliente();
        cliente.setNome("cliente teste" + number);
        cliente.setIdade(number);
        cliente.setSexo(((number %2 ) == 0 )? "M" : "F");
        LocalDate data = LocalDate.of(number, number, number);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        cliente.setAniversario(data.format(formatter));
        return cliente;
    }

    public ClienteVO mockVO(Integer number) {
        ClienteVO cliente = new ClienteVO();
        cliente.setNome("cliente teste" + number);
        cliente.setIdade(number);
        cliente.setSexo(((number %2 ) == 0 )? "M" : "F");
        LocalDate data = LocalDate.of(number, number, number);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        cliente.setDataNascimento(data.format(formatter));
        return cliente;
    }
}
