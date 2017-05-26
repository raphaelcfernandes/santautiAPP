package santauti.app.Model.Ficha;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Infeccioso extends RealmObject implements Serializable{
    private int pacienteComInfeccao;

    public int getPacienteComInfeccao() {
        return pacienteComInfeccao;
    }

    public void setPacienteComInfeccao(int pacienteComInfeccao) {
        this.pacienteComInfeccao = pacienteComInfeccao;
    }
}
