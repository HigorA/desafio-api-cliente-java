package br.com.desafio.model;

import java.util.List;

public class MetaData {

    private Integer qtdClientes;
    private List<?> clientes;

    public MetaData() {
    }

    public MetaData(Integer qtdClientes, List<?> clientes) {
        this.qtdClientes = qtdClientes;
        this.clientes = clientes;
    }

    public Integer getQtdClientes() {
        return qtdClientes;
    }

    public void setQtdClientes(Integer qtdClientes) {
        this.qtdClientes = qtdClientes;
    }

    public List<?> getClientes() {
        return clientes;
    }

    public void setClientes(List<?> clientes) {
        this.clientes = clientes;
    }
}
