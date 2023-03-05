package br.com.desafio.model;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagedResource<T> {

    private List<T> clientes;
    private final Map<String, Number> metaData;

    public PagedResource(Page<T> page) {
        super();
        this.setClientes(page.getContent());
        this.metaData = new HashMap<>();
        populateMetadata(page);
    }

    private void populateMetadata(Page<T> page) {
        int size = page.getSize();
        int totalPages = page.getTotalPages();
        int number = page.getNumber();
        long total = page.getTotalElements();
        metaData.put("number", number);
        metaData.put("size", size);
        metaData.put("totalPages", totalPages);
        metaData.put("total", total);
    }

    public List<T> getClientes() {
        return clientes;
    }

    public void setClientes(List<T> clientes) {
        this.clientes = clientes;
    }

    public Map<String, Number> getMetaData() {
        return metaData;
    }
}
