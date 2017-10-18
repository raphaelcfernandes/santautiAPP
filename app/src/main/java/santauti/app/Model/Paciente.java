package santauti.app.Model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by rapha on 13-Oct-17.
 */
@IgnoreExtraProperties
public class Paciente {
    private int box;
    private int leito;
    private String profissionalResponsavel;
    private String nome;
    private String sobrenome;

    private String pacienteKey;

    public String getPacienteKey() {
        return pacienteKey;
    }

    public void setPacienteKey(String pacienteKey) {
        this.pacienteKey = pacienteKey;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Paciente() {
    }

    public Paciente(int box, int leito, String profissionalResponsavel, String nome, String sobrenome) {
        this.box = box;
        this.leito = leito;
        this.profissionalResponsavel = profissionalResponsavel;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public int getLeito() {
        return leito;
    }

    public void setLeito(int leito) {
        this.leito = leito;
    }

    public String getProfissionalResponsavel() {
        return profissionalResponsavel;
    }

    public void setProfissionalResponsavel(String profissionalResponsavel) {
        this.profissionalResponsavel = profissionalResponsavel;
    }
}
