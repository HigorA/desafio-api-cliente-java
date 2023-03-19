package br.com.desafio.model;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagedResource {

    private List<?> clientes;
    private final Map<String, Number> metaData;

    public PagedResource(Page<?> page) {
        super();
        this.setClientes(page.getContent());
        this.metaData = new HashMap<>();
        populateMetadata(page);
    }

    private void populateMetadata(Page<?> page) {
        int size = page.getSize();
        int totalPages = page.getTotalPages();
        int number = page.getNumber();
        long total = page.getTotalElements();
        metaData.put("number", number);
        metaData.put("size", size);
        metaData.put("totalPages", totalPages);
        metaData.put("total", total);
    }

    public List<?> getClientes() {
        return clientes;
    }

    public void setClientes(List<?> clientes) {
        this.clientes = clientes;
    }

    public Map<String, Number> getMetaData() {
        return metaData;
    }
}
