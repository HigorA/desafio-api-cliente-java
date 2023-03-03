package br.com.desafio.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteResponse implements Comparable<ClienteResponse>{

    @CsvBindByName
    @CsvBindByPosition(position = 0)
    private Long id;

    @CsvBindByName
    @CsvBindByPosition(position = 1)
    private String nome;

    @CsvBindByName
    @CsvBindByPosition(position = 2)
    private Integer idade;

    @CsvBindByName
    @CsvBindByPosition(position = 3)
    private String sexo;

    @CsvBindByName
    @CsvBindByPosition(position = 4)
    private String dataNascimento;

    public ClienteResponse() {
    }

    public ClienteResponse(String nome, Integer idade, String sexo, String dataNascimento) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public ClienteResponse(Long id, String nome, Integer idade, String sexo, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public int compareTo(ClienteResponse o) {
        if (getNome() == null || o.getNome() == null) {
            return 0;
        }
        return getNome().compareTo(o.getNome());
    }

    @Override
    public String toString() {
        return "ClienteResponse{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                '}';
    }
}
