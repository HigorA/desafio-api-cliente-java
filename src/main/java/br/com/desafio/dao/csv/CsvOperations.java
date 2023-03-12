package br.com.desafio.dao.csv;

import br.com.desafio.exceptions.ResourceNotFoundException;
import br.com.desafio.model.vo.ClienteVO;
import br.com.desafio.util.ClienteUtil;
import com.opencsv.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CsvOperations {

    private static final String FILE_PATH = "./src/main/resources/clientes.csv";

    public static List<ClienteVO> readCsv() {
        try {
            CsvToBean csvToBean = new CsvToBeanBuilder<>(Files.newBufferedReader(Path.of(FILE_PATH)))
                    .withType(ClienteVO.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return (List<ClienteVO>) csvToBean.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeAClienteCsv(ClienteVO clienteVO) {
        //TODO refazer para preparar o código para caso o arquivo ainda não exista
        List<ClienteVO> clientesFromCsv = readCsv();
        if (clienteVO.getDataNascimento().length() <= 5) {
            clienteVO.setDataNascimento(ClienteUtil.convertAniversario(clienteVO.getDataNascimento(), clienteVO.getIdade()));
        }
        clientesFromCsv.add(clienteVO);
        writeListToCsv(Arrays.asList(clienteVO));
    }

    public static void writeListToCsv(List<ClienteVO> clienteVOS) {
        //TODO o arquivo CSV não está com cabeçalho
        clienteVOS.forEach(c -> c.setId(null));
        clienteVOS = clienteVOS.stream().sorted(ClienteVO::compareTo).collect(Collectors.toList());
        List<ClienteVO> clientesComId = ClienteUtil.incrementId(clienteVOS);
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(FILE_PATH));
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            for (ClienteVO clienteVO : clientesComId) {
                beanToCsv.write(clienteVO);
            }
            writer.close();
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteRowFromCsv(Integer id) {
        //TODO o arquivo CSV não está com cabeçalho
        List<ClienteVO> clientesFromCsv = readCsv();

        boolean notFound = (clientesFromCsv.stream().noneMatch(o -> Objects.equals(o.getId(), id)));
        if (notFound) throw new ResourceNotFoundException("Não é possivel encontrar o registro a ser excluido.");

        List<ClienteVO> listaComElementoExcluido = clientesFromCsv.stream().filter(cliente -> !(cliente.getId() == id.longValue())).collect(Collectors.toList());
        writeListToCsv(listaComElementoExcluido);
    }

    public static void updateRowFromCsv(ClienteVO clienteVO) {
        //TODO o arquivo CSV não está com cabeçalho
        List<ClienteVO> clientesFromCsv = readCsv();
        List<ClienteVO> listaComElemento = clientesFromCsv.stream().filter(cliente -> (Objects.equals(cliente.getId(), clienteVO.getId()))).collect(Collectors.toList());

        if (listaComElemento.isEmpty()) throw new ResourceNotFoundException("Não possivel encontrar o registro a ser alterado.");

        ClienteVO alterado = listaComElemento.get(0);
        alterado.setNome(clienteVO.getNome());
        alterado.setIdade(clienteVO.getIdade());
        alterado.setSexo(clienteVO.getSexo());
        alterado.setDataNascimento(clienteVO.getDataNascimento());
        deleteRowFromCsv(alterado.getId());
        writeAClienteCsv(alterado);
    }
}
