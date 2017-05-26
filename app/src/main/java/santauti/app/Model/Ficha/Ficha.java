package santauti.app.Model.Ficha;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import santauti.app.Model.Paciente;
import santauti.app.Model.User;

/**
 * Created by Raphael Fernandes on 25-May-17.
 */

public class Ficha extends RealmObject implements Serializable{

    @PrimaryKey
    private int NroAtendimento;
    private Date dataCriado;
    private User user;
    private Paciente paciente;
    private Metabolico metabolico;

    public Metabolico getMetabolico() {
        return metabolico;
    }

    public void setMetabolico(Metabolico metabolico) {
        this.metabolico = metabolico;
    }

    public int getNroAtendimento() {
        return NroAtendimento;
    }

    public void setNroAtendimento(int nroAtendimento) {
        NroAtendimento = nroAtendimento;
    }

    public Date getDataCriado() {
        return dataCriado;
    }

    public void setDataCriado(Date dataCriado) {
        this.dataCriado = dataCriado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
