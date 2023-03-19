package br.com.desafio.dao.csv;

import br.com.desafio.exceptions.ResourceNotFoundException;
import br.com.desafio.model.vo.ClienteVO;
import br.com.desafio.util.ClienteUtil;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvOperations {

    private static String filePath = "src/main/resources/clientes2.csv";

    public static List<ClienteVO> readCsv() {
        try {
            Path path = Paths.get(filePath);
            boolean fileExists = Files.exists(path);
            if (!fileExists) {
                Files.createFile(path);
            }
            CsvToBean csvToBean = new CsvToBeanBuilder<>(Files.newBufferedReader(Path.of(filePath)))
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
        List<ClienteVO> clientesFromCsv = readCsv();
        if (clienteVO.getDataNascimento().length() <= 5) {
            clienteVO.setDataNascimento(ClienteUtil.convertAniversario(clienteVO.getDataNascimento(), clienteVO.getIdade()));
        }
        clientesFromCsv.add(clienteVO);
        writeListToCsv(clientesFromCsv);
    }

    public static void writeListToCsv(List<ClienteVO> clienteVOS) {
        clienteVOS.forEach(c -> c.setId(null));
        clienteVOS = clienteVOS.stream().sorted(ClienteVO::compareTo).collect(Collectors.toList());
        List<ClienteVO> clientesComId = ClienteUtil.incrementId(clienteVOS);
        HeaderColumnNameMappingStrategy<ClienteVO> strategy = new HeaderColumnNameMappingStrategy<>();
        Comparator<String> columnOrderComparator = new Comparator<String>() {
            List<String> columnOrder = Arrays.asList("ID", "NOME", "IDADE", "SEXO", "DATANASC");
            @Override
            public int compare(String o1, String o2) {
                int index1 = columnOrder.indexOf(o1);
                int index2 = columnOrder.indexOf(o2);
                return Integer.compare(index1,index2);
            }
        };
        strategy.setType(ClienteVO.class);
        strategy.setColumnOrderOnWrite(columnOrderComparator);
        try  {
            Writer writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.WRITE);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withMappingStrategy(strategy)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(clientesComId);
            writer.close();
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteRowFromCsv(Integer id) {
        List<ClienteVO> clientesFromCsv = readCsv();

        boolean notFound = (clientesFromCsv.stream().noneMatch(o -> Objects.equals(o.getId(), id)));
        if (notFound) throw new ResourceNotFoundException("Não é possivel encontrar o registro a ser excluido.");

        List<ClienteVO> listaComElementoExcluido = clientesFromCsv.stream().filter(cliente -> !(cliente.getId() == id.longValue())).collect(Collectors.toList());
        writeListToCsv(listaComElementoExcluido);
    }

    public static void updateRowFromCsv(ClienteVO clienteVO) {
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
