package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente implements Comparable<Cliente>{

    @CsvBindByName(column = "nome")
    private String nome;

    @CsvBindByName(column = "idade")
    private Integer idade;

    @CsvBindByName(column = "sexo")
    private String sexo;

    @CsvBindByName(column = "dataNasc")
    private String aniversario;

    public Cliente() {
    }

    public Cliente(String nome, Integer idade, String sexo, String aniversario) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.aniversario = aniversario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @JsonProperty("dataNascimento")
    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                ", aniversario='" + aniversario + '\'' +
                '}';
    }

    @Override
    public int compareTo(Cliente o) {
        if (getNome() == null || o.getNome() == null) {
            return 0;
        }
        return getNome().compareTo(o.getNome());
    }
}
