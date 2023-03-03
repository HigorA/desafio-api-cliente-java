package br.com.desafio.util;

import br.com.desafio.model.response.ClienteResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClienteUtil {

    public String convertAniversario(String aniversario, Integer idade) {
        String[] aniver = aniversario.split("-");
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear() - idade,
                Integer.parseInt(aniver[1]), Integer.parseInt(aniver[0]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return localDate.format(formatter);
    }

    public List<ClienteResponse> incrementId(List<ClienteResponse> lista) {
        AtomicInteger id = new AtomicInteger();
        List<ClienteResponse> responses = new ArrayList<>(lista);
        responses.forEach(c -> c.setId((long) id.addAndGet(1)));
        return responses;
    }
}
