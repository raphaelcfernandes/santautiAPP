package santauti.app.Model;

import java.util.Map;

import santauti.app.Model.Ficha.Ficha;

/**
 * Created by rapha on 12-Oct-17.
 */

public class Hospital {
    private String cidade;
    private String bairro;
    private String numero;
    private String avenida;
    private String cep;
    private String nome;
    private Map<String,Paciente> Pacientes;
    private Map<String,Profissional> Profissionais;
    private Map<String,Ficha> Fichas;



    private String hospitalDocumentKey;

    public String getHospitalDocumentKey() {
        return hospitalDocumentKey;
    }

    public void setHospitalDocumentKey(String hospitalDocumentKey) {
        this.hospitalDocumentKey = hospitalDocumentKey;
    }

    public Map<String, Profissional> getProfissionais() {
        return Profissionais;
    }

    public void setProfissionais(Map<String, Profissional> profissionais) {
        Profissionais = profissionais;
    }

    public Map<String, Ficha> getFichas() {
        return Fichas;
    }

    public void setFichas(Map<String, Ficha> fichas) {
        Fichas = fichas;
    }

    public Hospital() {
    }

    public Hospital(String cidade, String bairro, String numero, String avenida, String cep, String nome, Map<String, Paciente> pacientes, Map<String, Profissional> profissionais, Map<String, Ficha> fichas, String hospitalDocumentKey) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.avenida = avenida;
        this.cep = cep;
        this.nome = nome;
        Pacientes = pacientes;
        Profissionais = profissionais;
        Fichas = fichas;
        this.hospitalDocumentKey = hospitalDocumentKey;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Map<String, Paciente> getPacientes() {
        return Pacientes;
    }

    public void setPacientes(Map<String, Paciente> pacientes) {
        Pacientes = pacientes;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
