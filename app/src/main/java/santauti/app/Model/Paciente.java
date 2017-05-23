package santauti.app.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Raphael Fernandes on 23-May-17.
 */

public class Paciente implements Serializable {
    @SerializedName("ID")
    private int ID;
    @SerializedName("Leito")
    private int Leito;
    @SerializedName("Box")
    private int Box;
    @SerializedName("Profissao")
    private String Profissao;
    @SerializedName("Convenio")
    private String Convenio;
    @SerializedName("Internado")
    private int Internado;
    @SerializedName("Responsavel")
    private int Responsavel;
    @SerializedName("Nome")
    private String Nome;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @SerializedName("Sobrenome")
    private String Sobrenome;
    @SerializedName("NomeMedico")
    private String NomeMedico;
    @SerializedName("SobrenomeMedico")
    private String SobrenomeMedico;

    public int getInternado() {
        return Internado;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getNomeMedico() {
        return NomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        NomeMedico = nomeMedico;
    }

    public String getSobrenomeMedico() {
        return SobrenomeMedico;
    }

    public void setSobrenomeMedico(String sobrenomeMedico) {
        SobrenomeMedico = sobrenomeMedico;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLeito() {
        return Leito;
    }

    public void setLeito(int leito) {
        Leito = leito;
    }

    public int getBox() {
        return Box;
    }

    public void setBox(int box) {
        Box = box;
    }

    public String getProfissao() {
        return Profissao;
    }

    public void setProfissao(String profissao) {
        Profissao = profissao;
    }

    public String getConvenio() {
        return Convenio;
    }

    public void setConvenio(String convenio) {
        Convenio = convenio;
    }

    public int isInternado() {
        return Internado;
    }

    public void setInternado(int internado) {
        Internado = internado;
    }

    public int getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(int responsavel) {
        Responsavel = responsavel;
    }
}
