package br.com.desafio.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteRequest implements Comparable<ClienteRequest>{

    private String nome;
    private Integer idade;
    private String sexo;
    private LocalDate dataNascimento;

    public ClienteRequest() {
    }

    public ClienteRequest(String nome, Integer idade, String sexo, LocalDate dataNascimento) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
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

    public LocalDate getAniversario() {
        return dataNascimento;
    }

    public void setAniversario(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento='" + dataNascimento ;
    }

    @Override
    public int compareTo(ClienteRequest o) {
        if (getNome() == null || o.getNome() == null) {
            return 0;
        }
        return getNome().compareTo(o.getNome());
    }
}
