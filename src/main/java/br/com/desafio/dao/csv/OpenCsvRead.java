package br.com.desafio.dao.csv;

import br.com.desafio.model.response.ClienteResponse;
import com.opencsv.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenCsvRead {

    private static String filePath = "src/main/resources/clientes.csv";

    public static List<ClienteResponse> readCsv() {
        try {
            Map<String, String> mapping = new HashMap<>();
            mapping.put("ID", "id");
            mapping.put("NOME", "nome");
            mapping.put("IDADE", "idade");
            mapping.put("SEXO", "sexo");
            mapping.put("DATANASCIMENTO", "dataNascimento");

            HeaderColumnNameTranslateMappingStrategy<ClienteResponse> strategy =
                    new HeaderColumnNameTranslateMappingStrategy<>();

            strategy.setType(ClienteResponse.class);
            strategy.setColumnMapping(mapping);

            CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                    .withCSVParser(csvParser)
                    .build();
            CsvToBean csvToBean = new CsvToBean();
            csvToBean.setMappingStrategy(strategy);
            csvToBean.setCsvReader(csvReader);

            return (List<ClienteResponse>) csvToBean.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeAClienteCsv(ClienteResponse clienteResponse) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.APPEND);

            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(clienteResponse);
            writer.close();
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeListToCsv(List<ClienteResponse> clienteResponses) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(filePath));
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(clienteResponses);
            writer.close();
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteRowFromCsv(Integer id) {
        List<ClienteResponse> clientes = readCsv();
        List<ClienteResponse> listaComElementoExcluido = clientes.stream().filter(cliente -> !(cliente.getId() == id.longValue())).collect(Collectors.toList());
        writeListToCsv(listaComElementoExcluido);
    }
}
