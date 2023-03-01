package br.com.desafio.util;

import br.com.desafio.model.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClienteUtil {

    public String convertAniversario(Cliente cliente) {
        String[] aniversario = cliente.getAniversario().split("-");
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear() - cliente.getIdade(),
                Integer.parseInt(aniversario[1]), Integer.parseInt(aniversario[0]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return localDate.format(formatter);
    }
}
