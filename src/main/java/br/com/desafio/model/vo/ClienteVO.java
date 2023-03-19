package br.com.desafio.model.vo;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.NotBlank;


public class ClienteVO implements Comparable<ClienteVO>{

    @NotBlank
    @CsvBindByName(column = "id")
    private Integer id;

    @NotBlank
    @CsvBindByName(column = "nome")
    private String nome;

    @NotBlank
    @CsvBindByName(column = "idade")
    private Integer idade;

    @NotBlank
    @CsvBindByName(column = "sexo")
    private String sexo;

    @NotBlank
    @CsvBindByName(column = "dataNasc")
    private String dataNascimento;

    public ClienteVO() {
    }

    public ClienteVO(String nome, Integer idade, String sexo, String dataNascimento) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public ClienteVO(Integer id, String nome, Integer idade, String sexo, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public int compareTo(ClienteVO o) {
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
