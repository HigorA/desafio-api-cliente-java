package br.com.desafio.util;

import br.com.desafio.model.vo.ClienteVO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClienteUtil {

    public static String convertAniversario(String aniversario, Integer idade) {
        String[] aniver = aniversario.split("[-/]");
        if (Integer.parseInt(aniver[1]) > 12) {
            throw new IllegalArgumentException("String de aniversário inválida");
        }
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear() - idade,
                Integer.parseInt(aniver[1]), Integer.parseInt(aniver[0]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return localDate.format(formatter);
    }

    public static List<ClienteVO> incrementId(List<ClienteVO> lista) {
        AtomicInteger id = new AtomicInteger();
        List<ClienteVO> responses = new ArrayList<>(lista);
        responses.forEach(c -> c.setId(id.addAndGet(1)));
        return responses;
    }
}
