package br.com.desafio.search;

public class ClienteParametroFiltro {

    private Integer menorIdade;
    private Integer maiorIdade;
    private String sexo;
    private String mesAniversario;

    public ClienteParametroFiltro() {
    }

    public ClienteParametroFiltro(Integer menorIdade, Integer maiorIdade, String sexo, String mesAniversario) {
        this.menorIdade = menorIdade;
        this.maiorIdade = maiorIdade;
        this.sexo = sexo;
        this.mesAniversario = mesAniversario;
    }

    public Integer getMaiorIdade() {
        return maiorIdade;
    }

    public void setMaiorIdade(Integer maiorIdade) {
        this.maiorIdade = maiorIdade;
    }

    public Integer getMenorIdade() {
        return menorIdade;
    }

    public void setMenorIdade(Integer menorIdade) {
        this.menorIdade = menorIdade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMesAniversario() {
        return mesAniversario;
    }

    public void setMesAniversario(String mesAniversario) {
        this.mesAniversario = mesAniversario;
    }
}
